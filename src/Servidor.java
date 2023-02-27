import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor extends JFrame {
    private JTextField txtMensaje;
    private JButton btnEnviarServidor;
    private JPanel pnlMain;
    private JLabel lblTitle;
    private JTextArea txtAreaServidor;

    static ServerSocket servidor;
    static Socket cliente;
    static DataInputStream entrada;
    static DataOutputStream salida;

public Servidor() {
    btnEnviarServidor.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String mensaje = txtMensaje.getText().trim();
                salida.writeUTF(mensaje);
                txtMensaje.setText("");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    });
}

public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setContentPane(new Servidor().pnlMain);
    frame.setSize(500, 500);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

    try {
        String mensaje = "";
        // Crear el socket del servidor
        servidor = new ServerSocket(1201);
        // Aceptar conexiones
        cliente = servidor.accept();
        // Flujo de datos de entrada
        entrada = new DataInputStream(cliente.getInputStream());
        // Flujo de datos de salida
        salida = new DataOutputStream(cliente.getOutputStream());
        // Bucle para mantener la comunicación
        while (!mensaje.equals("salir")) {
            mensaje = entrada.readUTF();
            System.out.println("Cliente: " + mensaje);
            // Servidor servidorClase = new Servidor();
            // servidorClase.txtAreaServidor.setText(servidorClase.txtAreaServidor.getText().trim() + "\n" + mensaje);
            // txtAreaServidor.setText(txtAreaServidor.getText().trim() + "\n" + mensaje);
        }

        entrada.close();
        salida.close();
        cliente.close();
        servidor.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}



