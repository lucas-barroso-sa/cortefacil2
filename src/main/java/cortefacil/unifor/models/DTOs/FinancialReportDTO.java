package cortefacil.unifor.models.DTOs;

import java.util.ArrayList;
import java.util.List;

public class FinancialReportDTO {
    private Double totalRevenue;      // Faturamento Total da Barbearia
    private Double totalCommissions;  // Total a pagar de comissões
    private Double netProfit;         // Lucro Líquido (Receita - Comissões)

    private List<BarberFinancialDTO> barberReports = new ArrayList<>();

    public FinancialReportDTO() {
        this.totalRevenue = 0.0;
        this.totalCommissions = 0.0;
        this.netProfit = 0.0;
    }



    public Double getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(Double totalRevenue) { this.totalRevenue = totalRevenue; }
    public Double getTotalCommissions() { return totalCommissions; }
    public void setTotalCommissions(Double totalCommissions) { this.totalCommissions = totalCommissions; }
    public Double getNetProfit() { return netProfit; }
    public void setNetProfit(Double netProfit) { this.netProfit = netProfit; }
    public List<BarberFinancialDTO> getBarberReports() { return barberReports; }
    public void setBarberReports(List<BarberFinancialDTO> barberReports) { this.barberReports = barberReports; }
}
