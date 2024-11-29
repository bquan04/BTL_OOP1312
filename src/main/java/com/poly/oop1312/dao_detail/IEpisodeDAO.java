package com.poly.oop1312.dao_detail;

import java.util.List;
import com.poly.oop1312.model.Episode;

public interface IEpisodeDAO {
	List<Episode> findAllByFilmID(Integer id);
	List<Episode> findAllByFilmIdASC(Integer id);
	List<Episode> findAllLastestEpByFilmID();
	Episode findLastestUploaded(Integer id);
}
