package pwr.tictactoe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@SpringBootApplication
@EnableWebSocket
public class TicTacToeApplication implements WebSocketConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(TicTacToeApplication.class, args);
	}

	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		// No need for additional WebSocket handlers since we're using @ServerEndpoint
	}
}
