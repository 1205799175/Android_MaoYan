package com.yangyuning.maoyan.mode.bean;

import java.util.List;

/**
 * 城市地区的实体咧
 */
public class AreaBean {

	/**
	 * id : 1
	 * nm : 北京
	 * py : beijing
	 */

	private List<CtsBean> cts;

	public List<CtsBean> getCts() {
		return cts;
	}

	public void setCts(List<CtsBean> cts) {
		this.cts = cts;
	}

	public static class CtsBean {
		private int id;
		private String nm;
		private String py;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getNm() {
			return nm;
		}

		public void setNm(String nm) {
			this.nm = nm;
		}

		public String getPy() {
			return py;
		}

		public void setPy(String py) {
			this.py = py;
		}
	}

}
