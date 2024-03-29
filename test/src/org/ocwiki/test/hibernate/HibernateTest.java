package org.ocwiki.test.hibernate;


import org.ocwiki.controller.OcwikiApp;
import org.ocwiki.persistence.HibernateUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * Lớp cha của các lớp kiểm thử DAO:
 * <ul>
 * <li>Khởi tạo DB lấy dữ liệu từ tệp test/dataset/full.xml trước tất cả các hàm
 * test</li>
 * <li>Phục hồi một phần dữ liệu cần kiểm thử dựa vào dữ liệu trong tệp được chỉ
 * định khi khởi tạo lớp trước khi thực hiện mỗi hàm test</li>
 * <li>Đóng Hibernate session sau mỗi hàm test</li>
 * </ul>
 * 
 * @author cumeo89
 * 
 */
public class HibernateTest {

	private String datasetFile;

	public HibernateTest() {
	}

	public HibernateTest(String datasetFile) {
		super();
		this.datasetFile = datasetFile;
	}

	/**
	 * Khởi tạo cấu hình và tất cả các bảng
	 * @throws Exception
	 */
	@BeforeClass
	public static void setupClass() throws Exception {
		OcwikiApp.initialize("test/conf");
		// clean insert all tables
		HibernateTestUtil.cleanInsertDataset("test/dataset/full.xml");
	}

	/**
	 * Khởi tạo lại bảng được test case yêu cầu
	 * @throws Exception
	 */
	@Before
	public void setup() throws Exception {
		if (datasetFile != null) {
			HibernateTestUtil.cleanInsertDataset("test/dataset/" + datasetFile);
		}
	}

	@After
	public void tearDown() throws Exception {
		HibernateUtil.closeSession();
	}

}
