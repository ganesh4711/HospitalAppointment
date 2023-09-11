package com.main.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import com.main.RequestDto.PatientDto;
import com.main.entites.Patient;
import com.main.entites.User;
import com.main.globalExcp.BussinessException;
import com.main.repos.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.main.RequestDto.ReceptionStaffDto;
import com.main.entites.ReceptionStaff;
import com.main.repos.ReceptionStaffRepository;

@Service
public class ReceptionStaffService {

    @Autowired
    private ReceptionStaffRepository receptionRepo;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelmapper;

    public ReceptionStaffDto convertEntityToDto(ReceptionStaff staff) {
        modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelmapper.map(staff, ReceptionStaffDto.class);
    }

    public ReceptionStaff converDtoToEntity(ReceptionStaffDto receptionStaffDto) {
        return modelmapper.map(receptionStaffDto, ReceptionStaff.class);
    }

    /**
     * @param id staff id
     * @return staff details
     */
    public ReceptionStaffDto findReceptionInfoWithId(int id) {
        Optional<ReceptionStaff> byId = receptionRepo.findById(id);
        if (byId.isPresent()) {
            ReceptionStaff receptionStaff = byId.get();
            return convertEntityToDto(receptionStaff);
        }
        throw new NoSuchElementException();
    }

    /**
     *
     */
    public List<ReceptionStaffDto> findAllStaffInfo() {
        return receptionRepo.findAll().stream()
                .map(this::convertEntityToDto)
                .toList();
    }

    /**
     * @param offSet   page
     * @param pagesize of the page
     * @return page
     */
    public Page<ReceptionStaffDto> findAllStaffWithPagination(int offSet, int pagesize) {
        Page<ReceptionStaff> receptionStaffPage = receptionRepo.findAll(PageRequest.of(offSet, pagesize));
        List<ReceptionStaffDto> userDtoList = receptionStaffPage.getContent().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());

        return new PageImpl<>(userDtoList, receptionStaffPage.getPageable(), receptionStaffPage.getTotalElements());
    }

    /**
     * @param offSet   page
     * @param pagesize of the page
     * @param field    to be sorted
     * @return page
     */
    public Page<ReceptionStaffDto> findAllStaffWithPaginationSorting(int offSet, int pagesize, String field) {

        Page<ReceptionStaff> staffPage = receptionRepo.findAll(PageRequest.of(offSet, pagesize, Sort.by(field)));
        List<ReceptionStaffDto> receptionStaffDtosList = staffPage.getContent().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());

        return new PageImpl<>(receptionStaffDtosList, staffPage.getPageable(), staffPage.getTotalElements());
    }

    public ReceptionStaffDto addStaff(ReceptionStaffDto receptionStaffDto) {
        User user = userRepository.findById(25).get();
        if (user.getStatus()) {
            if (receptionStaffDto != null) {
                receptionStaffDto.setStaffName(user.getName());
                receptionStaffDto.setStatus(true);
                ReceptionStaff staff = converDtoToEntity(receptionStaffDto);
                return convertEntityToDto(receptionRepo.save(staff));

            } else throw new BussinessException("Patient Must not Be Null");
        } else throw new BussinessException("Deactivated User Unable to Process...");
    }

    /**
     * @param id patient to be deactivated
     * @return execution status
     */
    public boolean deleteStaffWithId(int id) {
        Optional<ReceptionStaff> optional = receptionRepo.findById(id);
        if (optional.isPresent()) {
            ReceptionStaff receptionStaff = optional.get();
            if (receptionStaff.getStatus()) {
                receptionStaff.setStatus(false);
                return true;
            }
        } else
            return false;

        return false;
    }
}


