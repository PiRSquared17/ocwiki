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
		bean.setContent(TextMapper.get().toBean(value.getContent()));
		ResourceReferenceMapper<Topic> topicMapper = ResourceReferenceMapper.get();
		MapperUtils.toBeans(bean.getTopics(), value.getTopics(), topicMapper);
		bean.setFilename(value.getFilename());
		bean.setAuthor(value.getAuthor());
		bean.setLicense(value.getLicense());
		bean.setOriginalSource(value.getOriginalSource());
		bean.setDateOfWork(value.getDateOfWork());
		bean.setAdditionalInfo(value.getAdditionalInfo());
		return bean;
	}

	@Override
	public File toEntity(FileBean value) {
		File entity = new File();
		entity.setId(value.getId());
		entity.setName(value.getName());
		entity.setNamespace(value.getNamespace());
		entity.setContent(TextMapper.get().toEntity(value.getContent()));
		ResourceReferenceMapper<Topic> topicMapper = ResourceReferenceMapper.get();
		MapperUtils.toEntities(value.getTopics(), entity.getTopics(), topicMapper);
		entity.setFilename(value.getFilename());
		entity.setAuthor(value.getAuthor());
		entity.setLicense(value.getLicense());
		entity.setOriginalSource(value.getOriginalSource());
		entity.setDateOfWork(value.getDateOfWork());
		entity.setAdditionalInfo(value.getAdditionalInfo());
		return entity;
	}

	private static FileMapper DEFAULT_INSTANCE = new FileMapper();

	public static FileMapper get() {
		return DEFAULT_INSTANCE;
	}

}
