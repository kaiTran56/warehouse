package com.tranquyet.sup.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.tranquyet.common.entities.BasedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "statistic_users")
@Getter
@Setter
public class StatisticUserEntity extends BasedEntity {

}
