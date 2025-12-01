package cortefacil.unifor.models.DTOs;

public class BarberFinancialDTO {
    private String barberName;
    private int totalAppointments; // Quantos cortes fez
    private Double totalBilled;    // Quanto faturou bruto
    private Double commissionRate; // % acordada
    private Double commissionValue;// Quanto vai receber (Faturado * %)

    public BarberFinancialDTO(String barberName, Double totalBilled, int totalAppointments, Double commissionRate) {
        this.barberName = barberName;
        this.totalBilled = totalBilled;
        this.totalAppointments = totalAppointments;
        this.commissionRate = commissionRate;
        // Calcula a comissão automaticamente
        if (commissionRate != null) {
            this.commissionValue = totalBilled * (commissionRate / 100);
        } else {
            this.commissionValue = 0.0;
        }
    }
    public BarberFinancialDTO() {}

    // Getters e Setters...
    public String getBarberName() { return barberName; }
    public int getTotalAppointments() { return totalAppointments; }
    public Double getTotalBilled() { return totalBilled; }
    public Double getCommissionRate() { return commissionRate; }
    public Double getCommissionValue() { return commissionValue; }
}
