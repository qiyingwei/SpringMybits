package cn.bdqn.DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.bdqn.Dao.Dao;
import cn.bdqn.shiTi.Branches;
import cn.bdqn.shiTi.Cityarea;
import cn.bdqn.util.util;

public class DaoImpl extends util implements Dao {
	ResultSet set=null;
	//查询全部信息
	@Override
	public List<Branches> selectByAll() {
		List<Branches> list = new ArrayList<Branches>();
		String sql = "select * from branches";
		set=super.Qurey(sql);
		try {
			while (set.next()) {
				list.add(new Branches(set.getInt("id"), set.getString("name"), set.getInt("CityAreaId"), set.getString("address"), set.getString("telephone")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.close();//关闭所有连接
		}
		return list;
	}
	//根据ID查询
	@Override
	public Branches selectById(int id) {
		Branches ches = null;
		String sql = "select * from branches where id = ?";
		set=super.Qurey(sql,id);
		try {
			while (set.next()) {
				ches = new Branches(set.getInt("id"), set.getString("name"), set
						.getInt("cityAreaId"), set.getString("address"), set
						.getString("telephone"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			super.close();//关闭所有连接
		}
		return ches;
	}
	@Override
	public List<Cityarea> selectChengShi() {
		List<Cityarea> shu=new ArrayList<Cityarea>();
		String sql = "select * from cityarea";
		set=super.Qurey(sql);
		try {
			while (set.next()) {
				shu.add(new Cityarea(set.getInt("id"), set.getString("name")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.close();//关闭所有连接
		}
		return shu;
	}
	@Override
	public int update(Branches b) {
		String sql = "UPDATE `branches`SET `name` = ?,`CityAreaId` = ?,`address` = ?,`telephone` = ? WHERE `id` = ?";
		Object[] obj ={b.getName(),b.getCityAreaId(),b.getAddress(),b.getTelephone(),b.getId()};
		int res = super.Update(sql, obj);
		return res;
	}

}
