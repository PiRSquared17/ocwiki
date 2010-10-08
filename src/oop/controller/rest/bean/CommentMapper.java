package oop.controller.rest.bean;

import oop.data.Comment;

public class CommentMapper implements Mapper<CommentBean, Comment> {

	@Override
	public CommentBean toBean(Comment value) {
		CommentBean bean = new CommentBean();
		bean.setId(value.getId());
		bean.setUser(UserReferenceMapper.get().toBean(value.getUser()));
		bean.setTimestamp(value.getTimestamp());
		bean.setResource(ResourceReferenceMapper.get().toBean(
				value.getResource()));
		bean.setRevision(RevisionReferenceMapper.get().toBean(
				value.getRevision()));
		return bean;
	}

	@Override
	public Comment toEntity(CommentBean value) {
		return null; //XXX
	}

	private static CommentMapper DEFAULT_INSTANCE = new CommentMapper();

	public static CommentMapper get() {
		return DEFAULT_INSTANCE;
	}

}
