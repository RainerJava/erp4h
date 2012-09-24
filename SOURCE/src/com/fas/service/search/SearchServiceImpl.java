package com.fas.service.search;

import java.util.List;

import com.fas.common.exception.BizException;
import com.fas.common.exception.DaoException;
import com.fas.dao.search.SearchDao;
import com.fas.dao.search.SearchDaoImpl;
import com.fas.service.base.BaseService;
import com.fas.vo.base.SortObjVo;
import com.fas.vo.search.MSchDspVo;
import com.fas.vo.search.SearchConditionVo;
import com.fas.vo.search.SearchVo;
import com.fas.vo.search.TrSchdspVo;

public class SearchServiceImpl extends BaseService implements SearchService {
	
	private SearchDao searchDao;
	
	/**
	 * <DL>
	 *   <DT>コンストラクター記述：</DT>
	 * 		<DD></DD>
	 * <BR>
	 *
	 * </DL>
	 */
	public SearchServiceImpl() {
		searchDao = new SearchDaoImpl();
	}
	
	/* (non-Javadoc)
	 * @see com.pipe.service.search.SearchService#getMSchDspVo(java.lang.String)
	 */
	public MSchDspVo getMSchDspVo(String key) throws BizException {
		try {
			return searchDao.getMSchDspVo(key);
		} catch (DaoException e) {
			throw new BizException(e);
		}
	}
	
	public List<SearchVo> getLstSearchVo(MSchDspVo dspVo, SearchConditionVo condition, SortObjVo sortObj) throws BizException {
		try {
			return searchDao.getLstSearchVo(dspVo, condition, sortObj);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BizException(e);
		}
	}

	public List<SearchVo> getLstSearchVo(TrSchdspVo dspVo, SearchConditionVo condition, SortObjVo sortObj) throws BizException {
		try {
			return searchDao.getLstSearchVo(dspVo, condition, sortObj);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BizException(e);
		}
	}
}