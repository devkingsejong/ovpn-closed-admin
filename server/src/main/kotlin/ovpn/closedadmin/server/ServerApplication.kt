package ovpn.closedadmin.server

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@SpringBootApplication
class ServerApplication {

    @Value("\${external.ip:127.0.0.1}")
    private val externalIp: String? = null

    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**")
                    .allowedOrigins(
                        "http://localhost:3000",
                        "http://${externalIp}:3000"
                    )
                    .allowedMethods("GET", "POST", "PUT", "DELETE")
                    .allowedHeaders("*")
                    .allowCredentials(true)
            }
        }
    }
}

fun main(args: Array<String>) {
    runApplication<ServerApplication>(*args)
}
