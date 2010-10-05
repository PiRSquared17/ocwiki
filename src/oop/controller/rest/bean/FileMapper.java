package oop.controller.rest.bean;

import oop.data.File;
import oop.data.Topic;

public class FileMapper implements Mapper<FileBean, File> {

	@Override
	public FileBean toBean(File value) {
		FileBean bean = new FileBean();
		bean.setId(value.getId());
		bean.setName(value.getName());
		bean.setNamespace(value.getNamespace());
		bean.setContent(value.getContent());
		ResourceReferenceMapper<Topic> topicMapper = ResourceReferenceMapper.get();
		MapperUtils.toBeans(bean.getTopics(), value.getTopics(), topicMapper);
		return null;
	}

	@Override
	public File toEntity(FileBean value) {
		File entity = new File();
		entity.setId(value.getId());
		entity.setName(value.getName());
		entity.setNamespace(value.getNamespace());
		entity.setContent(value.getContent());
		ResourceReferenceMapper<Topic> topicMapper = ResourceReferenceMapper.get();
		MapperUtils.toEntities(value.getTopics(), entity.getTopics(), topicMapper);
		return null;
	}

	private static FileMapper DEFAULT_INSTANCE = new FileMapper();

	public static FileMapper get() {
		return DEFAULT_INSTANCE;
	}

}
