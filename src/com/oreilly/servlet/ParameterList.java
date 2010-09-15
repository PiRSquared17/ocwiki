package com.oreilly.servlet;

public interface ParameterList {

	public String getString(String name) throws ParameterNotFoundException;

	public String getString(String name, String def);

	public boolean getBoolean(String name) throws ParameterNotFoundException;

	public boolean getBoolean(String name, boolean def);

	public byte getByte(String name) throws ParameterNotFoundException,
			NumberFormatException;

	public byte getByte(String name, byte def);

	public char getChar(String name) throws ParameterNotFoundException;

	public char getChar(String name, char def);

	public double getDouble(String name) throws ParameterNotFoundException,
			NumberFormatException;

	public double getDouble(String name, double def);

	public float getFloat(String name) throws ParameterNotFoundException,
			NumberFormatException;

	public float getFloat(String name, float def);

	public int getInt(String name) throws ParameterNotFoundException,
			NumberFormatException;

	public int getInt(String name, int def);

	public long getLong(String name) throws ParameterNotFoundException,
			NumberFormatException;

	public long getLong(String name, long def);

	public short getShort(String name) throws ParameterNotFoundException,
			NumberFormatException;

	public short getShort(String name, short def);

	public boolean hasParameter(String name);

	public int count(String name);

	public String getIndexedString(String name, int index)
			throws ParameterNotFoundException;

	public String getIndexedString(String name, int index, String def);

	public boolean getIndexedBoolean(String name, int index)
			throws ParameterNotFoundException;

	public boolean getIndexedBoolean(String name, int index, boolean def);

	public byte getIndexedByte(String name, int index)
			throws ParameterNotFoundException, NumberFormatException;

	public byte getIndexedByte(String name, int index, byte def);

	public char getIndexedChar(String name, int index)
			throws ParameterNotFoundException;

	public char getIndexedChar(String name, int index, char def);

	public double getIndexedDouble(String name, int index)
			throws ParameterNotFoundException, NumberFormatException;

	public double getIndexedDouble(String name, int index, double def);

	public float getIndexedFloat(String name, int index)
			throws ParameterNotFoundException, NumberFormatException;

	public float getIndexedFloat(String name, int index, float def);

	public int getIndexedInt(String name, int index)
			throws ParameterNotFoundException, NumberFormatException;

	public int getIndexedInt(String name, int index, int def);

	public long getIndexedLong(String name, int index)
			throws ParameterNotFoundException, NumberFormatException;

	public long getIndexedLong(String name, int index, long def);

	public short getIndexedShort(String name, int index)
			throws ParameterNotFoundException, NumberFormatException;

	public short getIndexedShort(String name, int index, short def);

	public String get(String name);

}