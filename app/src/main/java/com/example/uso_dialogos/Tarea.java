package com.example.uso_dialogos;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Tarea implements Parcelable {

    private String asignatura;
    private String descripcion;
    private String fechaEntrega;
    private String horaEntrega;
    private String estado;

    public Tarea(String asignatura, String fechaEntrega, String descripcion, String horaEntrega,  String estado) {
        this.asignatura = asignatura;
        this.descripcion = descripcion;
        this.fechaEntrega = fechaEntrega;
        this.horaEntrega = horaEntrega;
        this.estado = estado;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(String horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    protected Tarea(Parcel in){
        asignatura = in.readString();
        descripcion = in.readString();
        fechaEntrega = in.readString();
        horaEntrega = in.readString();
        estado = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(this.asignatura);
        parcel.writeString(this.descripcion);
        parcel.writeString(this.fechaEntrega);
        parcel.writeString(this.horaEntrega);
        parcel.writeString(this.estado);
    }

    public static final Creator<Tarea> CREATOR = new Creator<Tarea>() {
        @Override
        public Tarea createFromParcel(Parcel parcel) {
            return new Tarea(parcel);
        }

        @Override
        public Tarea[] newArray(int i) {
            return new Tarea[i];
        }
    };

    @Override
    public String toString() {
        return "Tarea: " +
                "asignatura: " + asignatura + '\n' +
                "descripcion: " + descripcion + '\n' +
                "fecha de entrega: " + fechaEntrega + '\n' +
                "hora de entrega: " + horaEntrega + '\n' +
                "estado: " + estado;
    }
}
