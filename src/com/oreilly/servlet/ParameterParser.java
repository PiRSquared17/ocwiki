package com.oreilly.servlet;

import javax.servlet.ServletRequest;

import com.mysql.jdbc.StringUtils;

public class ParameterParser implements ParameterList {
	private ServletRequest req;

	public ParameterParser(ServletRequest req) {
		this.req = req;
	}

	public String get(String name) {
		// Use getParameterValues() to avoid the once-deprecated getParameter()
		String[] values = req.getParameterValues(name);
		if (values == null || values[0].length() == 0)
			return null;
		else
			return values[0]; // ignore multiple field values
	}
	
	/* (non-Javadoc)
	 * @see com.oreilly.servlet.ParameterList#getString(java.lang.String)
	 */
	public String getString(String name)
			throws ParameterNotFoundException {
		// Use getParameterValues() to avoid the once-deprecated getParameter()
		String[] values = req.getParameterValues(name);
		if (values == null)
			throw new ParameterNotFoundException(name + " not found");
		else if (values[0].length() == 0)
			throw new ParameterNotFoundException(name + " was empty");
		else
			return values[0]; // ignore multiple field values
	}

	/* (non-Javadoc)
	 * @see com.oreilly.servlet.ParameterList#getString(java.lang.String, java.lang.String)
	 */
	public String getString(String name, String def) {
		try {
			return getString(name);
		} catch (Exception e) {
			return def;
		}
	}

	/* (non-Javadoc)
	 * @see com.oreilly.servlet.ParameterList#getBoolean(java.lang.String)
	 */
	public boolean getBoolean(String name)
			throws ParameterNotFoundException {
		return Boolean.valueOf(getString(name)).booleanValue();
	}

	/* (non-Javadoc)
	 * @see com.oreilly.servlet.ParameterList#getBoolean(java.lang.String, boolean)
	 */
	public boolean getBoolean(String name, boolean def) {
		try {
			return getBoolean(name);
		} catch (Exception e) {
			return def;
		}
	}

	/* (non-Javadoc)
	 * @see com.oreilly.servlet.ParameterList#getByte(java.lang.String)
	 */
	public byte getByte(String name)
			throws ParameterNotFoundException, NumberFormatException {
		return Byte.parseByte(getString(name));
	}

	/* (non-Javadoc)
	 * @see com.oreilly.servlet.ParameterList#getByte(java.lang.String, byte)
	 */
	public byte getByte(String name, byte def) {
		try {
			return getByte(name);
		} catch (Exception e) {
			return def;
		}
	}

	/* (non-Javadoc)
	 * @see com.oreilly.servlet.ParameterList#getChar(java.lang.String)
	 */
	public char getChar(String name) throws ParameterNotFoundException {
		String param = getString(name);
		if (param.length() == 0) // shouldn't be possible
			throw new ParameterNotFoundException(name + " is empty string");
		else
			return (param.charAt(0));
	}

	/* (non-Javadoc)
	 * @see com.oreilly.servlet.ParameterList#getChar(java.lang.String, char)
	 */
	public char getChar(String name, char def) {
		try {
			return getChar(name);
		} catch (Exception e) {
			return def;
		}
	}

	/* (non-Javadoc)
	 * @see com.oreilly.servlet.ParameterList#getDouble(java.lang.String)
	 */
	public double getDouble(String name)
			throws ParameterNotFoundException, NumberFormatException {
		return new Double(getString(name)).doubleValue();
	}

	/* (non-Javadoc)
	 * @see com.oreilly.servlet.ParameterList#getDouble(java.lang.String, double)
	 */
	public double getDouble(String name, double def) {
		try {
			return getDouble(name);
		} catch (Exception e) {
			return def;
		}
	}

	/* (non-Javadoc)
	 * @see com.oreilly.servlet.ParameterList#getFloat(java.lang.String)
	 */
	public float getFloat(String name)
			throws ParameterNotFoundException, NumberFormatException {
		return new Float(getString(name)).floatValue();
	}

	/* (non-Javadoc)
	 * @see com.oreilly.servlet.ParameterList#getFloat(java.lang.String, float)
	 */
	public float getFloat(String name, float def) {
		try {
			return getFloat(name);
		} catch (Exception e) {
			return def;
		}
	}

	/* (non-Javadoc)
	 * @see com.oreilly.servlet.ParameterList#getInt(java.lang.String)
	 */
	public int getInt(String name) throws ParameterNotFoundException,
			NumberFormatException {
		return Integer.parseInt(getString(name));
	}

	/* (non-Javadoc)
	 * @see com.oreilly.servlet.ParameterList#getInt(java.lang.String, int)
	 */
	public int getInt(String name, int def) {
		try {
			return getInt(name);
		} catch (Exception e) {
			return def;
		}
	}

	/* (non-Javadoc)
	 * @see com.oreilly.servlet.ParameterList#getLong(java.lang.String)
	 */
	public long getLong(String name)
			throws ParameterNotFoundException, NumberFormatException {
		return Long.parseLong(getString(name));
	}

	/* (non-Javadoc)
	 * @see com.oreilly.servlet.ParameterList#getLong(java.lang.String, long)
	 */
	public long getLong(String name, long def) {
		try {
			return getLong(name);
		} catch (Exception e) {
			return def;
		}
	}

	/* (non-Javadoc)
	 * @see com.oreilly.servlet.ParameterList#getShort(java.lang.String)
	 */
	public short getShort(String name)
			throws ParameterNotFoundException, NumberFormatException {
		return Short.parseShort(getString(name));
	}

	/* (non-Javadoc)
	 * @see com.oreilly.servlet.ParameterList#getShort(java.lang.String, short)
	 */
	public short getShort(String name, short def) {
		try {
			return getShort(name);
		} catch (Exception e) {
			return def;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.oreilly.servlet.ParameterList#hasParameter(java.lang.String)
	 */
	public boolean hasParameter(String name) {
		return !StringUtils.isNullOrEmpty(req.getParameter(name));
	}
	
	public int count(String name) {
		String[] values = req.getParameterValues(name);
		if (values == null) {
			return 0;
		}
		return values.length;
	}

	public String getIndexedString(String name, int index)
			throws ParameterNotFoundException {
		String[] values = req.getParameterValues(name);
		if (values == null)
			throw new ParameterNotFoundException(name + " not found");
		else if (values[index].length() == 0)
			throw new ParameterNotFoundException(name + " was empty");
		return values[index];
	}
	
	public String getIndexedString(String name, int index, String def) {
		try {
			return getIndexedString(name, index);
		} catch (ParameterNotFoundException e) {
			return def;
		}
	}

	/* (non-Javadoc)
	 * @see com.oreilly.servlet.ParameterList#getBoolean(java.lang.String)
	 */
	public boolean getIndexedBoolean(String name, int index)
			throws ParameterNotFoundException {
		return Boolean.valueOf(getIndexedString(name, index)).booleanValue();
	}

	/* (non-Javadoc)
	 * @see com.oreilly.servlet.ParameterList#getBoolean(java.lang.String, boolean)
	 */
	public boolean getIndexedBoolean(String name, int index, boolean def) {
		try {
			return getIndexedBoolean(name, index);
		} catch (Exception e) {
			return def;
		}
	}

	/* (non-Javadoc)
	 * @see com.oreilly.servlet.ParameterList#getByte(java.lang.String)
	 */
	public byte getIndexedByte(String name, int index)
			throws ParameterNotFoundException, NumberFormatException {
		return Byte.parseByte(getIndexedString(name, index));
	}

	/* (non-Javadoc)
	 * @see com.oreilly.servlet.ParameterList#getByte(java.lang.String, byte)
	 */
	public byte getIndexedByte(String name, int index, byte def) {
		try {
			return getIndexedByte(name, index);
		} catch (Exception e) {
			return def;
		}
	}

	/* (non-Javadoc)
	 * @see com.oreilly.servlet.ParameterList#getChar(java.lang.String)
	 */
	public char getIndexedChar(String name, int index) throws ParameterNotFoundException {
		String param = getIndexedString(name, index);
		if (param.length() == 0) // shouldn't be possible
			throw new ParameterNotFoundException(name + " is empty string");
		else
			return (param.charAt(0));
	}

	/* (non-Javadoc)
	 * @see com.oreilly.servlet.ParameterList#getChar(java.lang.String, char)
	 */
	public char getIndexedChar(String name, int index, char def) {
		try {
			return getIndexedChar(name, index);
		} catch (Exception e) {
			return def;
		}
	}

	/* (non-Javadoc)
	 * @see com.oreilly.servlet.ParameterList#getDouble(java.lang.String)
	 */
	public double getIndexedDouble(String name, int index)
			throws ParameterNotFoundException, NumberFormatException {
		return new Double(getIndexedString(name, index)).doubleValue();
	}

	/* (non-Javadoc)
	 * @see com.oreilly.servlet.ParameterList#getDouble(java.lang.String, double)
	 */
	public double getIndexedDouble(String name, int index, double def) {
		try {
			return getIndexedDouble(name, index);
		} catch (Exception e) {
			return def;
		}
	}

	/* (non-Javadoc)
	 * @see com.oreilly.servlet.ParameterList#getFloat(java.lang.String)
	 */
	public float getIndexedFloat(String name, int index)
			throws ParameterNotFoundException, NumberFormatException {
		return new Float(getIndexedString(name, index)).floatValue();
	}

	/* (non-Javadoc)
	 * @see com.oreilly.servlet.ParameterList#getFloat(java.lang.String, float)
	 */
	public float getIndexedFloat(String name, int index, float def) {
		try {
			return getIndexedFloat(name, index);
		} catch (Exception e) {
			return def;
		}
	}

	/* (non-Javadoc)
	 * @see com.oreilly.servlet.ParameterList#getInt(java.lang.String)
	 */
	public int getIndexedInt(String name, int index) throws ParameterNotFoundException,
			NumberFormatException {
		return Integer.parseInt(getIndexedString(name, index));
	}

	/* (non-Javadoc)
	 * @see com.oreilly.servlet.ParameterList#getInt(java.lang.String, int)
	 */
	public int getIndexedInt(String name, int index, int def) {
		try {
			return getIndexedInt(name, index);
		} catch (Exception e) {
			return def;
		}
	}

	/* (non-Javadoc)
	 * @see com.oreilly.servlet.ParameterList#getLong(java.lang.String)
	 */
	public long getIndexedLong(String name, int index)
			throws ParameterNotFoundException, NumberFormatException {
		return Long.parseLong(getIndexedString(name, index));
	}

	/* (non-Javadoc)
	 * @see com.oreilly.servlet.ParameterList#getLong(java.lang.String, long)
	 */
	public long getIndexedLong(String name, int index, long def) {
		try {
			return getIndexedLong(name, index);
		} catch (Exception e) {
			return def;
		}
	}

	/* (non-Javadoc)
	 * @see com.oreilly.servlet.ParameterList#getShort(java.lang.String)
	 */
	public short getIndexedShort(String name, int index)
			throws ParameterNotFoundException, NumberFormatException {
		return Short.parseShort(getIndexedString(name, index));
	}

	/* (non-Javadoc)
	 * @see com.oreilly.servlet.ParameterList#getShort(java.lang.String, short)
	 */
	public short getIndexedShort(String name, int index, short def) {
		try {
			return getIndexedShort(name, index);
		} catch (Exception e) {
			return def;
		}
	}
		
}
