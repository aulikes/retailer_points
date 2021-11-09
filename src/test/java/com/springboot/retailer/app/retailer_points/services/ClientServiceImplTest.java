package com.springboot.retailer.app.retailer_points.services;

import com.springboot.retailer.app.retailer_points.entities.Client;
import com.springboot.retailer.app.retailer_points.exceptions.ResourceAlreadyExists;
import com.springboot.retailer.app.retailer_points.exceptions.ResourceNotFoundException;
import com.springboot.retailer.app.retailer_points.repositories.ClientRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static com.springboot.retailer.app.retailer_points.DataClient.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ClientServiceImplTest {

    @MockBean
    ClientRepository clientRepository;

    @Autowired
    ClientService clientService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll() {
        when(clientRepository.findAll()).thenReturn(CLIENTS);
        List<Client> list = clientService.findAll();

        assertFalse(list.isEmpty());
        assertEquals(3, list.size());
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    void findByIdTrue() {
        when(clientRepository.findById(anyLong())).thenReturn(CLIENT_OPT01);

        Client client1 = clientService.findById(1L);
        Client client2 = clientService.findById(1L);

        assertSame(client1, client2);
        assertEquals("ANDRÉS", client1.getFirstName());
        assertEquals("ANDRÉS", client2.getFirstName());

        verify(clientRepository, times(2)).findById(1L);
    }

    @Test
    void findByIdFalse() {
        when(clientRepository.findById(1L)).thenReturn(CLIENT_OPT01);
        when(clientRepository.findById(2L)).thenReturn(CLIENT_OPT02);

        Client client1 = clientService.findById(1L);
        Client client2 = clientService.findById(2L);

        assertNotSame(client1, client2);
        verify(clientRepository, times(2)).findById(anyLong());
    }

    @Test
    void findByIdException() {
        when(clientRepository.findById(anyLong())).thenReturn(Optional.empty());
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> clientService.findById(10L));
        assertEquals(ResourceNotFoundException.class, exception.getClass());
    }

    @Test
    void saveTrue() {
        when(clientRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(clientRepository.save(any(Client.class))).thenReturn(CLIENT_SAVE01);

        Client client = clientService.save(CLIENT_SAVE01);

        assertEquals(client.getId(), CLIENT_SAVE01.getId());
        assertEquals(client.getFirstName(), CLIENT_SAVE01.getFirstName());
        assertEquals(client.getLastName(), CLIENT_SAVE01.getLastName());

        verify(clientRepository).findById(anyLong());
        verify(clientRepository).save(any(Client.class));
    }

    @Test
    void saveException() {
        when(clientRepository.findById(anyLong())).thenReturn(CLIENT_OPT01);

        Exception exception = assertThrows(ResourceAlreadyExists.class, () -> clientService.save(CLIENT_SAVE01));

        verify(clientRepository).findById(anyLong());
        verify(clientRepository, never()).save(any(Client.class));
        assertEquals(ResourceAlreadyExists.class, exception.getClass());
    }

    @Test
    void updateTrue() {
        when(clientRepository.findById(anyLong())).thenReturn(CLIENT_OPT02);
        when(clientRepository.save(any(Client.class))).thenReturn(CLIENT_SAVE02);

        Client client = clientService.update(CLIENT_SAVE02);

        assertEquals(client.getId(), CLIENT_SAVE02.getId());
        assertEquals(client.getFirstName(), CLIENT_SAVE02.getFirstName());
        assertEquals(client.getLastName(), CLIENT_SAVE02.getLastName());

        verify(clientRepository).findById(anyLong());
        verify(clientRepository).save(any(Client.class));
    }

    @Test
    void updateTrueException() {
        when(clientRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(clientRepository.save(any(Client.class))).thenReturn(CLIENT_SAVE02);

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> clientService.update(CLIENT_SAVE02));

        assertEquals(ResourceNotFoundException.class, exception.getClass());
        verify(clientRepository).findById(anyLong());
        verify(clientRepository, never()).save(any(Client.class));
    }
}