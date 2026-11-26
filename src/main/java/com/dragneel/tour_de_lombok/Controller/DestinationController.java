package com.dragneel.tour_de_lombok.Controller;

import com.dragneel.tour_de_lombok.Model.Destination;
import com.dragneel.tour_de_lombok.Service.DestinationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/destination")
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    private static final Logger logger = LoggerFactory.getLogger(DestinationController.class);

    // Tampilkan semua destinasi (wisata + souvenir)
    @GetMapping("/tour")
    public String showTourDestinations(Model model) {
        logger.info("Berhasil Mengakses Halaman Destinasi");
        model.addAttribute("tourList", destinationService.getTourDestinations());
        model.addAttribute("souvenirList", destinationService.getSouvenirCenters());
        return "destination";
    }

    // Tampilkan detail berdasarkan ID
    @GetMapping("/detail/{id}")
    public String showDetail(@PathVariable Long id, Model model) {
        logger.info("Berhasil Mengakses Halaman Detail Destinasi");
        Destination destination = destinationService.findById(id);
        if (destination == null) {
            model.addAttribute("error", "Destination not found");
            return "error";
        }
        model.addAttribute("destination", destination);
        return "detail";
    }

    // Tambah data baru (via REST API)
    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<Destination> addDestination(@RequestBody Destination destination, @RequestParam String type) {
        logger.info("Berhasil menambah Destianasi");
        Destination saved = destinationService.addDestination(destination, type);
        return ResponseEntity.ok(saved);
    }

    // Hapus data berdasarkan ID
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteDestination(@PathVariable Long id) {
        logger.info("Berhasil menghapus Destianasi");
        boolean deleted = destinationService.deleteById(id);
        if (deleted) {
            return ResponseEntity.ok("Destination deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Destination not found");
        }
    }

    // Update data berdasarkan ID
    @PutMapping("/update/{id}")
    @ResponseBody
    public ResponseEntity<String> updateDestination(@PathVariable Long id, @RequestBody Destination updated) {
        logger.info("Berhasil Mengubah Destianasi");
        boolean success = destinationService.updateDestination(id, updated);
        if (success) {
            return ResponseEntity.ok("Destination updated successfully");
        } else {
            return ResponseEntity.status(404).body("Destination not found");
        }
    }

    // Endpoint REST untuk ambil semua data
    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<Destination>> getAllDestinations() {
        logger.info("Berhasil Menampilkan Seluruh Destianasi");
        return ResponseEntity.ok(destinationService.getAllDestinations());
    }
}