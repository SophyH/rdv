package rdv.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "specialite")
@SequenceGenerator(name = "seqSpecialite", sequenceName = "seq_specialite", initialValue = 100, allocationSize = 5)
public class Specialite {
	@Id
	@GeneratedValue(generator = "seqSpecialite", strategy = GenerationType.SEQUENCE)
	@Column(name = "id_specialite")
	private Integer id;
	@Column(name = "specialite_specialite", length = 100)
	private String specialite;
	@Column(name = "duree_specialite", length = 100)
	private Date duree;
	

}
