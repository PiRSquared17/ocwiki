package oop.controller.rest.bean;

import oop.data.Comment;

public class CommentMapper implements Mapper<CommentBean, Comment> {

	@Override
	public CommentBean toBean(Comment value) {
		CommentBean bean = new CommentBean();
		bean.setId(value.getId());
		bean.setUser(UserReferenceMapper.get().toBean(value.getUser()));
		bean.setTimestamp(value.getTimestamp());
		bean.setMessage(value.getMessage());
		bean.setResource(ResourceReferenceMapper.get().toBean(
				value.getResource()));
		bean.setRevision(RevisionReferenceMapper.get().toBean(
				value.getRevision()));
		return bean;
	}

	@Override
	public Comment toEntity(CommentBean value) {
		Comment entity = new Comment();
		entity.setId(value.getId());
		entity.setUser(UserReferenceMapper.get().toEntity(value.getUser()));
		entity.setTimestamp(value.getTimestamp());
		entity.setMessage(value.getMessage());
		entity.setResource(ResourceReferenceMapper.get().toEntity(value.getResource()));
		entity.setRevision(RevisionReferenceMapper.get().toEntity(value.getRevision()));
		return entity;
	}

	private static CommentMapper DEFAULT_INSTANCE = new CommentMapper();

	public static CommentMapper get() {
		return DEFAULT_INSTANCE;
	}

}
