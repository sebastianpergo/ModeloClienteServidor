import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cliente extends JFrame {
    private JButton btnEnviarCliente;
    private JTextField txtMensaje;
    private JLabel lblTitle;
    private JTextArea textArea1;
    private JPanel pnlCliente;
    private JTextArea txtAreaCliente;

    static Socket cliente;
    static DataInputStream entrada;
    static DataOutputStream salida;
    public Cliente() {
        btnEnviarCliente.addActionListener(new ActionListener() {
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

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame();
        frame.setContentPane(new Cliente().pnlCliente);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

       try{
           cliente = new Socket("localhost", 1201);
           entrada = new DataInputStream(cliente.getInputStream());
           salida = new DataOutputStream(cliente.getOutputStream());
           String mensaje = "";
              while (!mensaje.equals("salir")){
                mensaje = entrada.readUTF();
                System.out.println("Servidor: " + mensaje);
                // Cliente clienteClase = new Cliente();
                // clienteClase.txtAreaCliente.setText(clienteClase.txtAreaCliente.getText().trim() + "\n" + mensaje);
              }
       }catch (Exception e){
           e.printStackTrace();
       }


    }
}
