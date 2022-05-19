package com.tranquyet.sup.algorithm.asset;

import java.util.ArrayList;
import java.util.List;

import com.tranquyet.sup.algorithm.model.TaskQuery;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Storage {
	private static Storage single_instance = null;
	private List<TaskQuery> tasks;

	public Storage() {
		tasks = new ArrayList<>();
	}

	public void addTaskQuery(TaskQuery task) {
		if (task == null || tasks.contains(task)) {
			return;
		}
		tasks.add(task);
	}

	public static Storage getInstance() {
		if (single_instance == null) {
			single_instance = new Storage();
		}

		return single_instance;
	}
}
