package com.hanoli.demojwt.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hanoli.demojwt.entity.Usuario;
import com.hanoli.demojwt.services.FolioService;

class FoliosRestControllerTest {

    @Test
    void getMisFoliosUsesClientIdFromAuthenticatedUser() {
        FolioService folioService = mock(FolioService.class);
        FoliosRestController controller = new FoliosRestController();
        controller.folioService = folioService;

        Usuario usuario = Usuario.builder().idCliente(60L).build();
        when(folioService.getFoliosPorCliente(60L)).thenReturn(List.of());

        ResponseEntity<?> response = controller.getMisFolios(usuario);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(folioService).getFoliosPorCliente(60L);
    }

    @Test
    void getMisFoliosRejectsUserWithoutAssociatedClient() {
        FolioService folioService = mock(FolioService.class);
        FoliosRestController controller = new FoliosRestController();
        controller.folioService = folioService;

        Usuario usuario = Usuario.builder().build();

        ResponseEntity<?> response = controller.getMisFolios(usuario);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        verifyNoInteractions(folioService);
    }
}
