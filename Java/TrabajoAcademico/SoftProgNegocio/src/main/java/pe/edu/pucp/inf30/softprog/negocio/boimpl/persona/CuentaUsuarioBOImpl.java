/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.inf30.softprog.negocio.boimpl.persona;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import pe.edu.pucp.inf30.softprog.dao.persona.CuentaUsuarioDAO;
import pe.edu.pucp.inf30.softprog.daoimpl.persona.CuentaUsuarioDAOImpl;
import pe.edu.pucp.inf30.softprog.modelo.persona.CuentaUsuario;
import pe.edu.pucp.inf30.softprog.modelo.persona.utils.PasswordUtils;
import pe.edu.pucp.inf30.softprog.modelo.persona.utils.TokenUtils;
import pe.edu.pucp.inf30.softprog.negocio.bo.persona.CuentaUsuarioBO;
import pe.edu.pucp.inf30.softprog.negocio.bo.persona.EmailService;

/**
 *
 * @author Cristhian Horacio
 */
public class CuentaUsuarioBOImpl implements CuentaUsuarioBO{
    private final CuentaUsuarioDAO cuentaDAO;
    private final EmailService emailService;
    
    private static final int MINUTOS_EXPIRACION = 60;
    private static final String BASE_URL_RESET = "https://mi-sistema.com/reset-password?token=";
    
    @Override
    public void solicitarRecuperacionPassword(String correo) {
        if (correo == null || correo.isBlank())
            throw new IllegalArgumentException("Debe indicar el correo.");

        // 1. Buscar usuario por correo
        CuentaUsuario cuenta = cuentaDAO.obtenerPorCorreo(correo);

        // Por seguridad: podrías NO decir si existe o no el correo,
        // pero igual no lanzamos error visible al usuario final.
        if (cuenta == null) {
            // Opcional: simplemente retornar para no dar pistas
            return;
        }

        // 2. Generar token y expiración
        String token = TokenUtils.generarTokenSeguro(32);
        Timestamp expira = Timestamp.valueOf(
                java.time.LocalDateTime.now().plusMinutes(MINUTOS_EXPIRACION)
        );

        // 3. Guardar en BD
        boolean ok = cuentaDAO.actualizarTokenRecuperacion(
                cuenta.getId(), token, expira
        );
        if (!ok) throw new RuntimeException("No se pudo registrar el token de recuperación.");

        // 4. Construir link y enviar correo
        String link = BASE_URL_RESET + token;

        String asunto = "Recuperación de contraseña";
        String cuerpo = "<p>Hola,</p>"
                + "<p>Has solicitado recuperar tu contraseña.</p>"
                + "<p>Haz clic en el siguiente enlace para restablecerla (válido por "
                + MINUTOS_EXPIRACION + " minutos):</p>"
                + "<p><a href=\"" + link + "\">Restablecer contraseña</a></p>"
                + "<p>Si no solicitaste este cambio, ignora este mensaje.</p>";

        emailService.enviarCorreo(correo, asunto, cuerpo);
    }
    
    public CuentaUsuarioBOImpl(){
        cuentaDAO = new CuentaUsuarioDAOImpl();
        this.emailService = new EmailServiceImpl();
    }
    
    @Override
    public List<CuentaUsuario> listar() {
        return this.cuentaDAO.leerTodos();
    }

    @Override
    public void insertar(CuentaUsuario modelo) {
        if(modelo == null){
            throw new IllegalArgumentException("La Cuenta Usuario  no puede ser nula");
        }     
        
        this.cuentaDAO.crear(modelo);
    }

    @Override
    public void actualizar(CuentaUsuario modelo) {
        if(modelo == null){
            throw new IllegalArgumentException("La Cuenta Usuario  no puede ser nula");
        } 
        
        if(modelo.getId() <= 0){
             throw new IllegalArgumentException("Cuenta Usuario con ID inválido");     
        }

        CuentaUsuario cuentaModelo = this.cuentaDAO.leer(modelo.getId());
        
        if(cuentaModelo == null){
            throw new RuntimeException("No se encontro a la Cuenta Usuario con id : "+modelo.getId());
        }
        
        this.cuentaDAO.actualizar(modelo);
    }

    @Override
    public CuentaUsuario obtener(int id) {
        if(id <= 0){
             throw new IllegalArgumentException("Cuenta Usuario con ID inválido");     
        }
        
        CuentaUsuario cuentaModelo = this.cuentaDAO.leer(id);
        
        if(cuentaModelo == null){
            throw new RuntimeException("No se encontro a la Cuenta Usuario con id : "+id);
        }
        
        return cuentaModelo;
    }

    @Override
    public void eliminar(int id) {
        if(id <= 0){
             throw new IllegalArgumentException("Cuenta Usuario con ID inválido");     
        }
        
        CuentaUsuario cuentaModelo = this.cuentaDAO.leer(id);
        
        if(cuentaModelo == null){
            throw new RuntimeException("No se encontro a la Cuenta Usuario con id : "+id);
        }
                
        this.cuentaDAO.eliminar(id);
    }
    @Override
    public boolean login(String email, String password){
        if(email.isEmpty() || email.isBlank() || password.isBlank() || password.isEmpty()){
            throw new IllegalArgumentException("Usuario o password inválido");
        }
        
        return this.cuentaDAO.login(email, password);
    }
    
    @Override
    public boolean cambiarPassword(String username, String passwordActual, String passwordNueva) {
        if (username == null || passwordActual == null || passwordNueva == null) {
            throw new IllegalArgumentException("Datos incompletos para cambio de contraseña");
        }

        CuentaUsuario cuenta = this.cuentaDAO.obtenerPorUsername(username);
        if (cuenta == null) {
            throw new RuntimeException("No se encontró la cuenta de usuario");
        }

        // Verificar contraseña actual
        boolean coincide = PasswordUtils.checkPassword(passwordActual, cuenta.getPassword());
        if (!coincide) {
            // puedes lanzar un tipo de excepción más específico
            throw new RuntimeException("La contraseña actual no es correcta");
        }

        // Hashear nueva contraseña
        String nuevoHash = PasswordUtils.hashPassword(passwordNueva);

        // Actualizar en BD
        boolean actualizado = cuentaDAO.actualizarPassword(cuenta.getId(), nuevoHash);
        if (!actualizado) {
            throw new RuntimeException("No se pudo actualizar la contraseña");
        }

        return true;
    }

    @Override
    public CuentaUsuario obtenerCuentaUsuarioPorCorreo(String correo) {
        return cuentaDAO.obtenerPorCorreo(correo);
    }

    @Override
    public CuentaUsuario obtenerCuentaUsuarioPorUserName(String username) {
        return cuentaDAO.obtenerPorUsername(username);
    }

    @Override
    public CuentaUsuario obtenerPorResetToken(String token) {
        return cuentaDAO.obtenerPorResetToken(token);
    }

    @Override
    public void resetPasswordConToken(String token, String nuevaPassword) {
        if (token == null || token.isBlank() || nuevaPassword == null || nuevaPassword.isBlank()) {
            throw new IllegalArgumentException("Datos inválidos.");
        }

        CuentaUsuario cuenta = cuentaDAO.obtenerPorResetToken(token);
        if (cuenta == null) {
            throw new RuntimeException("Token inválido.");
        }

        // Validar expiración
        Timestamp ahora = Timestamp.valueOf(java.time.LocalDateTime.now());
        if (cuenta.getResetTokenExpira() == null || cuenta.getResetTokenExpira().before(ahora)) {
            throw new RuntimeException("El enlace de recuperación ha expirado.");
        }

        // Hashear nueva contraseña
        String hashNuevo = PasswordUtils.hashPassword(nuevaPassword);

        // Actualizar password y limpiar token
        boolean ok = cuentaDAO.actualizarPasswordYLimpiarToken(
                cuenta.getId(), hashNuevo
        );
        if (!ok) {
            throw new RuntimeException("No se pudo actualizar la contraseña.");
        }
    }

    @Override
    public boolean validarTokenRecuperacion(String token) {
        if (token == null || token.isBlank()) {
            return false;
        }

        CuentaUsuario cuenta = cuentaDAO.obtenerPorResetToken(token);
        if (cuenta == null) {
            return false; // token no existe
        }

        Timestamp ahora = Timestamp.valueOf(LocalDateTime.now());

        // Si no hay fecha o ya expiró → inválido
        if (cuenta.getResetTokenExpira() == null || cuenta.getResetTokenExpira().before(ahora)) {
            return false;
        }

        return true; // existe y no expiró
    }
}
