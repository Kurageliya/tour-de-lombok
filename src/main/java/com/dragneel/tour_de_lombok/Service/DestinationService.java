package com.dragneel.tour_de_lombok.Service;

import com.dragneel.tour_de_lombok.Model.Destination;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DestinationService {

    private final List<Destination> tourList = new ArrayList<>();
    private final List<Destination> souvenirList = new ArrayList<>();

    public DestinationService() {
        // Dummy data destinasi wisata
        tourList.add(new Destination(1L, "Tanjung Aan Beach", "Central Lombok", "White sand and turquoise water."));
        tourList.add(new Destination(2L, "Senggigi Beach", "West Lombok", "Popular beach with sunset views."));
        tourList.add(new Destination(3L, "Mount Rinjani", "North Lombok", "Indonesia’s second-highest volcano."));
        tourList.add(new Destination(4L, "Gili Trawangan", "Northwest Lombok", "Island paradise with vibrant nightlife."));

        // Dummy data pusat oleh-oleh
        souvenirList.add(new Destination(101L, "Sasaku Lombok Merchandise", "Mataram & Senggigi", "Modern souvenir center with food, fashion, and pearls."));
        souvenirList.add(new Destination(102L, "Cakranegara Market", "Mataram", "Traditional market selling woven fabrics and snacks."));
        souvenirList.add(new Destination(103L, "Pearl Jewelry Center", "Senggigi", "High-quality pearl jewelry and accessories."));
    }

    // Ambil semua destinasi wisata
    public List<Destination> getTourDestinations() {
        return tourList;
    }

    // Ambil semua pusat oleh-oleh
    public List<Destination> getSouvenirCenters() {
        return souvenirList;
    }

    // Gabungkan semua destinasi
    public List<Destination> getAllDestinations() {
        List<Destination> all = new ArrayList<>();
        all.addAll(tourList);
        all.addAll(souvenirList);
        return all;
    }

    // Cari berdasarkan ID
    public Destination findById(Long id) {
        return getAllDestinations().stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Tambah destinasi baru ke kategori tertentu
    public Destination addDestination(Destination destination, String type) {
        if ("tour".equalsIgnoreCase(type)) {
            tourList.add(destination);
        } else if ("souvenir".equalsIgnoreCase(type)) {
            souvenirList.add(destination);
        }
        return destination;
    }

    // Hapus destinasi berdasarkan ID
    public boolean deleteById(Long id) {
        return tourList.removeIf(d -> d.getId().equals(id)) ||
                souvenirList.removeIf(s -> s.getId().equals(id));
    }

    // Update destinasi berdasarkan ID
    public boolean updateDestination(Long id, Destination updated) {
        for (int i = 0; i < tourList.size(); i++) {
            if (tourList.get(i).getId().equals(id)) {
                tourList.set(i, updated);
                return true;
            }
        }
        for (int i = 0; i < souvenirList.size(); i++) {
            if (souvenirList.get(i).getId().equals(id)) {
                souvenirList.set(i, updated);
                return true;
            }
        }
        return false;
    }
}