package oop.controller.rest.bean;

import oop.data.CommentReport;

public class CommentReportMapper implements
		Mapper<CommentReportBean, CommentReport> {

	@Override
	public CommentReportBean toBean(CommentReport value) {
		CommentReportBean bean = new CommentReportBean();
		bean.setComment(CommentMapper.get().toBean(value.getComment()));
		bean.setStatus(value.getStatus());
		bean.setLikeCount(value.getLikeCount());
		return bean;
	}

	@Override
	public CommentReport toEntity(CommentReportBean value) {
		throw new UnsupportedOperationException("readonly bean");
	}

	private static CommentReportMapper DEFAULT_INSTANCE = new CommentReportMapper();

	public static CommentReportMapper get() {
		return DEFAULT_INSTANCE;
	}

}
