package cortefacil.unifor.services;

import cortefacil.unifor.models.DTOs.user.UserDTO;
import cortefacil.unifor.models.DTOs.user.UserInsertDTO;
import cortefacil.unifor.models.entities.User;
import cortefacil.unifor.models.exceptions.DataBaseException;
import cortefacil.unifor.models.exceptions.ResourceNotFoundException;
import cortefacil.unifor.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public List<UserDTO> findAll() {
        List<User> list = userRepository.findAll();
        // Converte Entidade -> UserDTO (Sem senha)
        return list.stream().map(UserDTO::new).toList();
    }

    public UserDTO findById(Long id) {
        User entity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id: " + id));
        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO insert(UserInsertDTO dto) {
        User entity = new User();
        updateEntityFromDTO(entity, dto);


        entity.setPassword(passwordEncoder.encode(dto.getPassword()));

        entity = userRepository.save(entity);

        return new UserDTO(entity);
    }

    //fazer segurança no update password
    @Transactional
    public UserDTO update(Long id, UserInsertDTO dto) {
        User entity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found " + id));

        updateEntityFromDTO(entity, dto);

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        entity = userRepository.save(entity);
        return new UserDTO(entity);
    }

    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário não encontrado com id: " + id);
        }
        try {
            userRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Integridade referencial: Usuário possui agendamentos vinculados.");
        }
    }


    private void updateEntityFromDTO(User entity, UserInsertDTO dto) {

        if (dto.getName() != null && !dto.getName().isBlank()) {
            entity.setName(dto.getName());
        }


        if (dto.getUsername() != null && !dto.getUsername().isBlank()) {
            entity.setUsername(dto.getUsername()); // Supondo que na entidade o campo é 'login' ou 'username'
        }


        if (dto.getType() != null) {
            entity.setType(dto.getType());
        }

        if (dto.getCommissionPercentage() != null) {
            entity.setCommissionPercentage(dto.getCommissionPercentage());
        }

    }

    public User findEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado com id: " + id));
    }
}