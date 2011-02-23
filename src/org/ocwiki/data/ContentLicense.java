package org.ocwiki.data;

public enum ContentLicense {

	UNKNOWN,
	
	/**
	 * Giấy phép <a
	 * href="http://creativecommons.org/licenses/by/3.0/deed.en">Creative
	 * Commons 3.0</a>
	 */
	CC3,

	/**
	 * Giấy phép <a
	 * href="http://creativecommons.org/licenses/by-sa/3.0/deed.en">Creative
	 * Commons - Share Alike 3.0</a>
	 */
	CC_SA3,

	/**
	 * Giấy phép <a
	 * href="http://en.wikipedia.org/wiki/GNU_Free_Documentation_License">GNU
	 * Free Documentation License</a>
	 */
	GFPL,

	/**
	 * Kết hợp 2 giấy phép <a
	 * href="http://creativecommons.org/licenses/by-sa/3.0/deed.en">Creative
	 * Commons 3.0</a> và <a
	 * href="http://en.wikipedia.org/wiki/GNU_Free_Documentation_License">GNU
	 * Free Documentation License</a>
	 */
	CC_SA3_GFDL,

	/**
	 * <a href="http://en.wikipedia.org/wiki/Public_domain">Không nắm giữ bản
	 * quyền</a>
	 */
	PUBLIC_DOMAIN,

	/**
	 * <a href="http://creativecommons.org/publicdomain/zero/1.0/deed.en">CC0
	 * waiver</a>
	 */
	CREATIVE_COMMONS_ZERO_WAIVER

}
