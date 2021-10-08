package dinhnguyen.techs.testing.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "book")
public class Book {

	public Book() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private Long id;

	@Column(name = "name", unique = true)
	private String name;

	@Column(name = "code")
	private String code;

	@Column(name = "price")
	private Float price;

	@Version
	private Long version;

	public Book(String name, Float price) {
		super();
		this.name = name;
		this.price = price;
	}

	public Book(String name, Float price, Author author) {
		super();
		this.name = name;
		this.price = price;
		this.author = author;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "author_id", nullable = false)
	private Author author;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

}
