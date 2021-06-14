package com.nt.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.persistence.ParameterMode;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;

import com.nt.entity.InsurancePolicy;
import com.nt.utility.HibernateUtil;

//Procedure

/*CREATE OR REPLACE PROCEDURE P_GET_POLICIES_BY_TENURE 
(
  MINTENURE IN NUMBER 
, MAXTENURE IN NUMBER 
, DETAILS OUT SYS_REFCURSOR 
) AS 
BEGIN
  OPEN DETAILS FOR
    SELECT POLICYID,POLICYNAME,POLICYTYPE,COMPANY,TENURE FROM INSURANCEPOLICY WHERE TENURE>=MINTENURE AND TENURE<=MAXTENURE;
END P_GET_POLICIES_BY_TENURE;*/

//Function

/*CREATE OR REPLACE FUNCTION FX_GET_POLICY_DETAILS_BY_ID 
(
  ID IN NUMBER 
, NAME OUT VARCHAR2 
, DURATION OUT NUMBER 
) RETURN VARCHAR2 AS 
  FIRM VARCHAR2(20);
BEGIN
    SELECT POLICYNAME,COMPANY,TENURE INTO NAME,FIRM,DURATION FROM INSURANCEPOLICY WHERE POLICYID=ID;
  RETURN FIRM;
END FX_GET_POLICY_DETAILS_BY_ID;*/

public class InsurancePolicyDAOImpl implements IInsurancePolicyDAO {

	@Override
	public List<InsurancePolicy> getPoliciesByTenure(int min, int max) {
		Session session = null;
		Transaction tx = null;
		List<InsurancePolicy> list = null;
		ProcedureCall call = null;
		Output output = null;
		ResultSetOutput rsOutput = null;
		//get session obj
		session = HibernateUtil.getSession();
		//get tx or begin tx
		if(!session.getTransaction().isActive())
			tx = session.beginTransaction();
		//create ProcedureCall obj
		call = session.createStoredProcedureCall("P_GET_POLICIES_BY_TENURE",InsurancePolicy.class);
		//set IN,OUT params using JDBC Types and set IN params with values
		call.registerParameter(1, Integer.class, ParameterMode.IN).bindValue(min);
		call.registerParameter(2, Integer.class, ParameterMode.IN).bindValue(max);
		call.registerParameter(3, Class.class, ParameterMode.REF_CURSOR);
		//call PL/SQL Procedure
		output = call.getOutputs().getCurrent();
		rsOutput = (ResultSetOutput) output;
		//get ResultList from rsOutput
		list = rsOutput.getResultList();
		return list;
	}

	@Override
	public String[] getPolicyByID(int id) {
		Session session = null;
		Transaction tx = null;
		//get session obj
		session = HibernateUtil.getSession();
		//get tx or begin tx
		if(!session.getTransaction().isActive())
			tx = session.beginTransaction();
		String result[] = session.doReturningWork(new ReturningWork<String[]>() { //Anonymous Inner Class 

			@Override
			public String[] execute(Connection con) throws SQLException {
				CallableStatement cs = null;
				String outputs[] = null;
				//create CallableStatement Obj
				cs = con.prepareCall("{?=call FX_GET_POLICY_DETAILS_BY_ID(?,?,?)}");
				//set OUT,RETURN params with JDBC Types
				cs.registerOutParameter(1, Types.VARCHAR);
				cs.registerOutParameter(3, Types.VARCHAR);
				cs.registerOutParameter(4, Types.INTEGER);
				// set IN param with values
				cs.setInt(2, id);
				//execute PL/SQL Function
				cs.execute();
				//get results from OUT,RETURN params
				outputs = new String[3];
				outputs[0] = cs.getString(1);//name
				outputs[1] = cs.getString(3);//company
				outputs[2] = String.valueOf(cs.getInt(4));
				return outputs;
			}//Anonymous Inner Class Method
		});//Anonymous Inner Class and OuterClass Method Call
		
		return result;
	}//method

}//class
