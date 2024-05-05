package com.example.hospital_management_system.entity;

import com.example.hospital_management_system.enums.StaffType;

import jakarta.persistence.*;

@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "password", length = 25, nullable = false)
    private String password;

    @Column(name = "firstname", length = 25, nullable = false)
    private String firstName;

    @Column(name = "lastname", length = 25, nullable = false)
    private String lastName;

    @Column(name = "address", length = 100, nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "stafftype", nullable = false)
    private StaffType staffType;

    @ManyToOne
    @JoinColumn(name = "policlinicid")
    private Policlinic policlinic;
    // Constructors, getters, setters
    public Staff() {
    }

    public Staff(Long id, String firstName, String lastName, String address, Policlinic policlinic) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.policlinic = policlinic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
    public Policlinic getPoliclinic() {
        return policlinic;
    }

    public void setPoliclinic(Policlinic policlinic) {
        this.policlinic = policlinic;
    }

    public StaffType getStaffType() {
        return staffType;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", policlinic=" + policlinic +
                '}';
    }
} 
