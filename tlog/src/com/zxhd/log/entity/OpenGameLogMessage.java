package com.zxhd.log.entity;
import com.zxhd.log.core.ILogCodec;
import com.zxhd.log.file.LogNameUtil;
import com.zxhd.log.util.LogFileUtil;
import com.zxhd.log.util.IoBufferUtil;
import org.apache.mina.common.ByteBuffer;
import java.util.List;
import java.util.ArrayList;
/**
  * 连接登录服日志
  *	@author VIC
  * 此类用工具生成，不可修改
  */
public class OpenGameLogMessage implements ILogCodec{
	
	public OpenGameLogMessage(){}
	
	public OpenGameLogMessage(int serverId,int iUserId,String uuid,String quDaoTag,String dActionTime){
					this.serverId = serverId;
					this.iUserId = iUserId;
					this.uuid = uuid;
					this.quDaoTag = quDaoTag;
					this.dActionTime = dActionTime;
			}
	// 事件id
	private long iEventId;
			// 服务器Id
		private int serverId;
			// 用户id
		private int iUserId;
			// 帐号
		private String uuid;
			// 渠道
		private String quDaoTag;
			// 发生时间
		private String dActionTime;
		public void setIEventId(long iEventId){
		this.iEventId = iEventId;
	}
	public long getIEventId(){
		return this.iEventId;
	}
			public void setServerId(int serverId){
			this.serverId = serverId;
		}
		public int getServerId(){
			return this.serverId;
		}
			public void setIUserId(int iUserId){
			this.iUserId = iUserId;
		}
		public int getIUserId(){
			return this.iUserId;
		}
			public void setUuid(String uuid){
			this.uuid = uuid;
		}
		public String getUuid(){
			return this.uuid;
		}
			public void setQuDaoTag(String quDaoTag){
			this.quDaoTag = quDaoTag;
		}
		public String getQuDaoTag(){
			return this.quDaoTag;
		}
			public void setDActionTime(String dActionTime){
			this.dActionTime = dActionTime;
		}
		public String getDActionTime(){
			return this.dActionTime;
		}
		//@Override
	public void encode(ByteBuffer buffer){
		buffer.putLong(iEventId);
								   buffer.putInt(serverId);
			 								   buffer.putInt(iUserId);
			 								   IoBufferUtil.putString(buffer, uuid);
			 								   IoBufferUtil.putString(buffer, quDaoTag);
			 								   IoBufferUtil.putString(buffer, dActionTime);
			 			}
	//@Override
	public void decode(ByteBuffer buffer){
		if(buffer.hasRemaining()){
			this.iEventId = buffer.getLong();
		}
									if(buffer.hasRemaining()){
				   this.serverId = buffer.getInt();
				}
			 									if(buffer.hasRemaining()){
				   this.iUserId = buffer.getInt();
				}
			 									if(buffer.hasRemaining()){
				   this.uuid = IoBufferUtil.getString(buffer);
				}
			 									if(buffer.hasRemaining()){
				   this.quDaoTag = IoBufferUtil.getString(buffer);
				}
			 									if(buffer.hasRemaining()){
				   this.dActionTime = IoBufferUtil.getString(buffer);
				}
			 			}
	
	//@Override
	public int getType() {
		
		return 10038;
	}

	//@Override
	public String getFileName() {
		return LogNameUtil.getLogName(this.getClass().getSimpleName());
	}

	//@Override
	public String getUpdateSql() {
		return "insert into openGameLog values(" + iEventId + "," + serverId+","+iUserId+","+"'" + uuid+ "'"+","+"'" + quDaoTag+ "'"+","+"'" + dActionTime+ "'" + ")";
	}

	//@Override
	public String getCreateTableSql() {
		return "create table openGameLog (iEventId bigint(20) comment '事件id', serverId int(11) comment'服务器Id',iUserId int(11) comment'用户id',uuid varchar(45) comment'帐号',quDaoTag varchar(45) comment'渠道',dActionTime timestamp comment'发生时间',primary key(iUserId, serverId, iEventId))ENGINE=MyISAM DEFAULT CHARSET=utf8;" ;
	}

	//@Override
	public String getFileContent() {
		return LogFileUtil.convertObjects2String(serverId,iUserId,uuid,quDaoTag,dActionTime);
	}
	
	public List<Object> list(){
		List<Object> list = new ArrayList<Object>();
		list.add(iEventId);
					list.add(serverId);
					list.add(iUserId);
					list.add(uuid);
					list.add(quDaoTag);
					list.add(dActionTime);
				return list;
	}
	
}