package com.olive.model;


import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Table;

import lombok.Data;
@Entity
@Table(name = "user_tab")
@Data
public class User {
@Id	
@GeneratedValue(strategy =GenerationType.AUTO )
private Integer id;
@Column(name = "usr_name")
private String username;
@Column(name = "usr_mail")
private String email;
@Column(name = "usr_pwd")
private String password;
@ElementCollection(fetch=FetchType.EAGER)
//@ElementCollection
@CollectionTable(name = "usr_role_tab",
joinColumns = @JoinColumn(name="uid"))
@Column(name = "usr_role")
private Set<String> role;
}
