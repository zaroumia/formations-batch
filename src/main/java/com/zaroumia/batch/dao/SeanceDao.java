package com.zaroumia.batch.dao;

import java.util.List;

import com.zaroumia.batch.domaine.PlanningItem;

public interface SeanceDao {

	int count();

	List<PlanningItem> getByFormateurId(Integer formateurId);
}
