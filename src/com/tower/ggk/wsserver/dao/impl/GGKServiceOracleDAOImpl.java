package com.tower.ggk.wsserver.dao.impl;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.LobCreator;

import com.tower.ggk.wsserver.dao.GGKServiceDAO;
import com.tower.resmaster.framework.jdbc.BaseDaoSupport;


public class GGKServiceOracleDAOImpl  extends BaseDaoSupport   implements GGKServiceDAO  {

	private static Logger _logger = Logger.getLogger(GGKServiceOracleDAOImpl.class);

	public void insertLog(String inxml,String interfaceCode,String system_source,String system_target,String action, String status, String notes,String msgId,String esbId) {
		
		_logger.info("insertLog_:"+inxml);

        SimpleDateFormat timemmsgid=new SimpleDateFormat("yyyyMMddHHmmssSSS"); 
        String logId=timemmsgid.format(new java.util.Date());
		try {
			storeMessateContent(getJdbcTemplate(), inxml, logId, interfaceCode, system_source, system_target,action,status,notes,msgId,esbId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

	
	public void storeMessateContent(JdbcTemplate jdbctemplate, final String inMsg,final String logId,final String interfaceCode,final String system_source,final String system_target,final String action,final String status,final String notes,final String msgId,final String esbId) throws SQLException { 
		final String inputXml = inMsg==null?"":inMsg;
		String sql = "";
		//sql = "insert into  ggk_interface_log (log_id ,DATA_CODE ,system_source,system_target,create_time,input_xml ,status,notes,action,msgId) ";
		//sql = sql + "values(?,?,?,?,sysdate,?,?,?,?,?) ";
		sql = " insert into  pub_interface_log (log_id ,INTERFACE_CODE ,system_source,system_target,START_time,input_xml ,EXCEPTION,EXCEPTION_DESC,action,msgId,esbId) ";
		sql = sql + "values(?,?,?,?,sysdate,?,?,?,?,?,?) ";
		try {
			jdbctemplate.execute(sql,
					new AbstractLobCreatingPreparedStatementCallback(getLobHandler()) {
						protected void setValues(PreparedStatement pstmt,LobCreator lobCreator) throws SQLException,
							DataAccessException {
							pstmt.setObject(1, logId);
							pstmt.setObject(2, interfaceCode);
							pstmt.setObject(3, system_source);
							pstmt.setObject(4, system_target);
							lobCreator.setClobAsCharacterStream(pstmt, 5,new StringReader(inputXml), inputXml.length()); // 设置详细的clob字段的内容
							pstmt.setObject(6, status);
							pstmt.setObject(7, notes);
							pstmt.setObject(8, action);
							pstmt.setObject(9, msgId);
							pstmt.setObject(10, esbId);
						}

					});
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new SQLException(ex.getMessage());
		}
		}


	public void chgProjectStatus(String itemCode, String status) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn=createConnection();
		StringBuffer sql = new StringBuffer();
		sql.setLength(0);
		sql.append(" update srv_business_pre a set a.cpmis_status=? where a.itemcode=? and a.delete_state='0' ");
        ps = conn.prepareStatement(sql.toString());
        _logger.error(sql.toString());
        _logger.error(itemCode);
        _logger.error(status);
        ps.setString(1, status==null?"5050011":status);
        ps.setString(2, itemCode);
        ps.executeUpdate();
        
		
		} catch (SQLException e) {
			_logger.error(e.getMessage(), e);
		} finally{
			cleanUp(conn, null, ps, rs);
		}
		
	}


	public void signProjectCode(String orderCode, String itemCode) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn=createConnection();
		StringBuffer sql = new StringBuffer();
		sql.setLength(0);
		sql.append(" update srv_business_pre a set a.itemcode=? where a.dis_seq=? and a.delete_state='0' ");
        ps = conn.prepareStatement(sql.toString());
        _logger.error(sql.toString());
        _logger.error(itemCode);
        _logger.error(orderCode);
        ps.setString(1, itemCode);
        ps.setString(2, orderCode);
        ps.executeUpdate();
        
		
		} catch (SQLException e) {
			_logger.error(e.getMessage(), e);
		} finally{
			cleanUp(conn, null, ps, rs);
		}
	}


	public void delUser(String userId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn=createConnection();
		StringBuffer sql = new StringBuffer();
		sql.setLength(0);
		sql.append(" delete from  P_HR_STAFF a  ");
		sql.append(" where srcusercode= ? ");
		ps = conn.prepareStatement(sql.toString());
        _logger.error(sql.toString());
        _logger.error(userId);
        ps.setString(1, userId);
        ps.executeUpdate();
		
		} catch (SQLException e) {
			_logger.error(e.getMessage(), e);
		} finally{
			cleanUp(conn, null, ps, rs);
		}
		
	}


	public String getUser(String userId) {
		String rtn=null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn=createConnection();
		StringBuffer sql = new StringBuffer();
		sql.setLength(0);
		sql.append(" select srcusercode from P_HR_STAFF where P_HR_STAFF=? ");
	     ps = conn.prepareStatement(sql.toString());
	     ps.setString(1, userId);
        rs  = ps.executeQuery();
        if(rs.next()){
        	rtn=rs.getString("srcusercode");
        }
       
		
		} catch (SQLException e) {
			_logger.error(e.getMessage(), e);
		} finally{
			cleanUp(conn, null, ps, rs);
		}
		return rtn;
	}


	public void insertUser(HashMap<String, String> user) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn=createConnection();
		StringBuffer sql = new StringBuffer();
		sql.setLength(0);
		/*<srcusercode Note=\"门户uid号\" ifEvent=\"0,1,2\"></srcusercode>");
		defiInfo.append("			<ctalias Note=\"门户登录名\" ifEvent=\"0,1,2\"></ctalias>");
		defiInfo.append("			<idcardnumber Note=\"身份证号码\" ifEvent=\"0,1,2\"></idcardnumber>");
		defiInfo.append("			<employnum Note=\"员工编码\" ifEvent=\"0,1,2\"></employnum>");
		defiInfo.append("			<username Note=\"姓名\" ifEvent=\"0,1\"></username>");
		defiInfo.append("			<gender Note=\"性别\" ifEvent=\"0,1\">,1,2,</gender>");
		defiInfo.append("			<sdeptcode Note=\"所在组织唯一编码\" ifEvent=\"0,1\"></sdeptcode>");
		defiInfo.append("			<positioncode Note=\"岗位层级编码\" ifEvent=\"0,1\"></positioncode>");
		defiInfo.append("			<title Note=\"岗位名称\" ifEvent=\"0,1\"></title>");
		defiInfo.append("			<mobile Note=\"联系方式/手机号\" ifEvent=\"0,1\"></mobile>");
		defiInfo.append("			<ctusertype Note=\"用户类型\" ifEvent=\"0,1\">,1,2,3,4,9,</ctusertype>");
		defiInfo.append("			<oldsdeptcode Note=\"原所在组织唯一编码\" ifEvent=\"0,1\"></oldsdeptcode>");
		
		//defiInfo.append("			<titlecode Note=\"职务编码\" Need=\"false\">,1101,1102,1103,1104,1105,1201,1202,1203,1204,1205,1206,1207,1208,1209,1210,1211,1212,2101,2102,2103,2104,2105,2201,2202,2203,204,2205,2206,2207,2208,2209,2210,2211,2212,3101,3102,3103,3104,3105,3201,3202,3203,3204,3205,3206,3207,3208,3209,3210,3211,3212,4101,4102,4103,4104,4105,4201,4202,4203,4204,4205,4206,4207,4208,4209,4210,4211,4212,</titlecode>");
		defiInfo.append("			<status Note=\"用户状态\" Need=\"false\">,1,0,</status>");
		
		defiInfo.append("			<dataType Note=\"数据类型\" Need=\"false\">,1,2,4,8,16,</dataType>");
		defiInfo.append("			<hrOperType Note=\"HR业务操作类型\" Need=\"false\">0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,100,101,102,103,104,105,106,</hrOperType>");
		defiInfo.append("			<operType Note=\"操作类型\" Need=\"false\">,1,,</operType>");
		defiInfo.append("			<ctStatus Note=\"人员状态\" Need=\"false\">,101,102,103,104,105,106,107,108,109,110,111,201,202,203,204,205,206,207,299,3,</ctStatus>");
		defiInfo.append("			<ctPosLevelType Note=\"岗位等级体系\" Need=\"false\">,1,2,</ctPosLevelType>");
		defiInfo.append("			<ctPositionLevel Note=\"岗位等级\" Need=\"false\">,100,200,300,400,500,600,700,800,900,1000,1100,1200,1300,1400,1500,9900,9999,28,27,26,25,24,3,22,21,20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1,0,</ctPositionLevel>");
		defiInfo.append("			<ctPosLayerType Note=\"岗位层级体系\" Need=\"false\">,1,2,</ctPosLayerType>");
		defiInfo.append("			<ctPositionLayer Note=\"岗位层级\" Need=\"false\">,100,200,300,400,500,600,700,00,900,1000,1100,1200,1300,400,1500,9900,9999,Z01001,Z01002,Z01003,Z01004,Z01005,Z01006,01007,Z01008,Z01009,Z01010,Z02001,Z02002,Z02003,Z02004,Z02999,</ctPositionLayer>");
		defiInfo.append("			<ctPositionSequence Note=\"岗位序列\" Need=\"false\">,1,2,</ctPositionSequence>");
		defiInfo.append("			<ctGender Note=\"性别\" Need=\"false\">,Z01,Z02,</ctGender>");
		defiInfo.append("			<userStatus Note=\"用户状态\" Need=\"false\">,1,2,</userStatus>");
		defiInfo.append("			<pType Note=\"任职类型\" Need=\"false\">,1,2,3,</pType>");
		defiInfo.append("			<gctUserType Note=\"员工类型\" Need=\"false\">,101,10201,10
*/		sql.append(" insert into P_HR_STAFF (srcusercode,ctalias,idcardnumber,username,sdeptcode,uni_code,hr_i_d,login_name,parent_code,Ct_Corp_Code) ");
		sql.append(" values(?,?,?,?,?,?,?,?,?,?) ");
		ps = conn.prepareStatement(sql.toString());
        _logger.error(sql.toString());
        _logger.error(user.toString());
        ps.setString(1, user.get("srcusercode").toString());
        ps.setString(2, user.containsKey("ctalias")?"":user.get("ctalias").toString());
        ps.setString(3, user.containsKey("idcardnumber")?"":user.get("idcardnumber").toString());
        ps.setString(4, user.containsKey("username")?"":user.get("username").toString());
        ps.setString(5, user.containsKey("sdeptcode")?"":user.get("sdeptcode").toString());
        ps.setString(6, user.containsKey("uniCode")?"":user.get("uniCode").toString());
        ps.setString(7, user.containsKey("hrID")?"":user.get("hrID").toString());
        ps.setString(8, user.containsKey("loginName")?"":user.get("loginName").toString());
        ps.setString(9, user.containsKey("parentCode")?"":user.get("parentCode").toString());
        ps.setString(10, user.containsKey("ctCorpCode")?"":user.get("ctCorpCode").toString());
        ps.executeUpdate();
        
		
		} catch (SQLException e) {
			_logger.error(e.getMessage(), e);
		} finally{
			cleanUp(conn, null, ps, rs);
		}
		
	}


	public void updateUser(HashMap<String, String> user) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			conn=createConnection();
		StringBuffer sql = new StringBuffer();
		sql.setLength(0);
		sql.append(" update P_HR_STAFF a set ctalias=?,idcardnumber=?,username=?,sdeptcode=?,uni_code=?,hr_i_d=?,login_name=?,parent_code=?,Ct_Corp_Code=? ");
		sql.append(" where srcusercode= ? ");
		ps = conn.prepareStatement(sql.toString());
        _logger.error(sql.toString());
        _logger.error(user.toString());
        
        ps.setString(1, user.containsKey("ctalias")?"":user.get("ctalias").toString());
        ps.setString(2, user.containsKey("idcardnumber")?"":user.get("idcardnumber").toString());
        ps.setString(3, user.containsKey("username")?"":user.get("username").toString());
        ps.setString(4, user.containsKey("sdeptcode")?"":user.get("sdeptcode").toString());
        ps.setString(5, user.containsKey("uniCode")?"":user.get("uniCode").toString());
        ps.setString(6, user.containsKey("hrID")?"":user.get("hrID").toString());
        ps.setString(7, user.containsKey("loginName")?"":user.get("loginName").toString());
        ps.setString(8, user.containsKey("parentCode")?"":user.get("parentCode").toString());
        ps.setString(9, user.containsKey("ctCorpCode")?"":user.get("ctCorpCode").toString());
        ps.setString(10, user.get("srcusercode").toString());
        ps.executeUpdate();
        
		
		} catch (SQLException e) {
			_logger.error(e.getMessage(), e);
		} finally{
			cleanUp(conn, null, ps, rs);
		}
	}


	/* (non-Javadoc)
	 * 获取公共库 配置的 对象需要更新对象的属性 
	 * @see com.tower.ggk.wsserver.dao.GGKServiceDAO#getPropertyByObjectCode(java.lang.String, java.lang.String)
	 */
	public List<Map<String,String>> getPropertyByObjectCode(String objectCode,String zrSystem) {
		String selSql="select DATAID, PROPERTY_CODE, IS_KEY, b.sys_no SYSTEM_ZR from ITF_OBJECT_PROPERTY_CONFIG a,pub_res_produce_consum_cfg c, pub_system b where a.cfg_id = c.cfg_id and c.sys_id=b.sys_id and a.datacode='"+objectCode+"' ";
		if(zrSystem!=null&&!"".equals(zrSystem)){
			selSql=selSql+" and b.sys_no='"+zrSystem+"' ";
		}
		return this.getJdbcTemplate().queryForList(selSql);
	}


	/**或许对象 需要下发的消费系统
	 * @param objectCode
	 * @param zrSystem
	 * @return
	 */
	public List<Map<String, String>> getSystemByObjectCode(String objectCode,String function,String zeSysNo) {
		StringBuffer selSql=new StringBuffer();
		selSql.append("select a.sys_no SYSTEM,b.interface_id,");
		selSql.append(" (select f1.para_desc from pub_para_conf f1 where f1.interface_id=b.interface_id and f1.para_code='Sender') SENDER,");
		selSql.append(" (select f1.para_desc from pub_para_conf f1 where f1.interface_id=b.interface_id and f1.para_code='ServCode') SERVCODE,");
		selSql.append("  (select f1.para_desc from pub_para_conf f1 where f1.interface_id=b.interface_id and f1.para_code='FUNCTION') FUNCTION,");
		selSql.append(" (select f1.para_desc from pub_para_conf f1 where f1.interface_id=b.interface_id and f1.para_code='ESBADDRESS') ESBADDRESS,");
		selSql.append("  (select f1.para_desc from pub_para_conf f1 where f1.interface_id=b.interface_id and f1.para_code='NAMESPACE') NAMESPACE");
		selSql.append("  from pub_system a,pub_res_produce_consum_cfg b,pub_res_type c");
		selSql.append(" where  c.database_name='").append(objectCode).append("' and b.sys_id=a.sys_id and c.res_type_id=b.res_type_id ");
		//剔除 发送数据过来的系统 这个系统 有可能是 责任对象系统 也有可能是责任属性系统
		selSql.append(" AND  A.SYS_NO NOT IN('").append(zeSysNo).append("') ");
		
		return this.getJdbcTemplate().queryForList(selSql.toString());
	}
	
	public int excuteSql(String insql) {
		//this.getJdbcTemplate().execute(insql);
		return this.getJdbcTemplate().update(insql);
		
	}


	public int getCountBySql(String insql) {
		return this.getJdbcTemplate().queryForInt(insql);
	}
	
}
