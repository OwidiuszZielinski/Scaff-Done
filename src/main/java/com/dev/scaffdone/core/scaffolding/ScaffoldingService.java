package com.dev.scaffdone.core.scaffolding;

import com.dev.scaffdone.core.scaffolding.dto.ScaffoldingDTO;
import com.dev.scaffdone.core.scaffolding.model.Scaffolding;
import com.dev.scaffdone.core.scaffolding.model.ScaffoldingModule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScaffoldingService {

    private final ScaffoldingDataRepository scaffoldingDataRepository;

    public ScaffoldingService(ScaffoldingDataRepository scaffoldingDataRepository) {
        this.scaffoldingDataRepository = scaffoldingDataRepository;
    }

    public ScaffoldingDTO add(ScaffoldingDTO scaffoldingDTO) {
        if(scaffoldingDTO.getModules().isEmpty()){
            throw new IllegalArgumentException("Scaffolding must have dimensions");
        }
        scaffoldingDTO.setDone(true);
        scaffoldingDTO.setTotalLength(calculateLength(scaffoldingDTO.getModules()));
        scaffoldingDTO.setResultSquareMeters(calculateSquareMeters(
                scaffoldingDTO.getTotalLength(),scaffoldingDTO.getHeight()
        ));
        Scaffolding newScaffolding = new Scaffolding();
        newScaffolding.fromDTO(scaffoldingDTO);
        scaffoldingDataRepository.save(newScaffolding);
        return scaffoldingDTO;
    }

    private float calculateSquareMeters(float totalLength, float height) {
        return totalLength * height;
    }

    public static float calculateLength(List<ScaffoldingModule> sizes) {
        return sizes.stream().map(e -> e.getDimension() * e.getQuantity()).reduce(0f, Float::sum);
    }

    public List<Scaffolding> getScaffolds() {
        return scaffoldingDataRepository.findAll();
    }
}
