package com.example.example.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "tipo_documento", uniqueConstraints ={
        @UniqueConstraint(name = "uk_sigla", columnNames = {"sigla"}),
        @UniqueConstraint(name = "uk_nombre_documento", columnNames = {"nombre_documento"})
} )
public class TipoDocumento implements Serializable {
    private static final long serialVersionUID = 56L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "sigla", length = 10, nullable = false)
    @NotNull
    private String sigla;
    @NotNull
    @Column(name = "nombre_documento", length = 100)
    private String nombreDocuento;
    @NotNull
    @Column(name = "estado_tipo_documento", length = 40)
    private String estadoTipoDocumento;

    @JsonIgnore
    @OneToMany(mappedBy = "tipoDocumento")
    private Set<Cliente> clienteList;

    public TipoDocumento() {
    }

    public TipoDocumento(@NotNull String sigla, @NotNull String nombreDocuento, @NotNull String estadoTipoDocumento) {
        this.sigla = sigla;
        this.nombreDocuento = nombreDocuento;
        this.estadoTipoDocumento = estadoTipoDocumento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNombreDocuento() {
        return nombreDocuento;
    }

    public void setNombreDocuento(String nombreDocuento) {
        this.nombreDocuento = nombreDocuento;
    }

    public String getEstadoTipoDocumento() {
        return estadoTipoDocumento;
    }

    public void setEstadoTipoDocumento(String estadoTipoDocumento) {
        this.estadoTipoDocumento = estadoTipoDocumento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TipoDocumento that = (TipoDocumento) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TipoDocumento{" +
                "id=" + id +
                ", sigla='" + sigla + '\'' +
                ", nombreDocuento='" + nombreDocuento + '\'' +
                ", estadoTipoDocumento='" + estadoTipoDocumento + '\'' +
                '}';
    }
}
