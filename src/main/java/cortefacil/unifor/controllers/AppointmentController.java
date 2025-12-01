package cortefacil.unifor.controllers;

import cortefacil.unifor.models.DTOs.AppointmentDTO;
import cortefacil.unifor.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public List<AppointmentDTO> findAll() {
        return appointmentService.findAll();
    }
    @PostMapping
    public AppointmentDTO insert(@RequestBody AppointmentDTO appointmentDTO) {
        return appointmentService.insert(appointmentDTO);
    }

}
