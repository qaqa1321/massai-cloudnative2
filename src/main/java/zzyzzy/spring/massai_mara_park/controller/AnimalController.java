package zzyzzy.spring.massai_mara_park.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zzyzzy.spring.massai_mara_park.model.Animal;
import zzyzzy.spring.massai_mara_park.service.AnimalService;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@Controller
public class AnimalController {

    private final AnimalService animalService;
    private final String title;
    private final int serverPort;
    private final String version;

    public AnimalController(AnimalService animalService,
                            @Value("${app.title}") String title,
                            @Value("${server.port:8080}") int serverPort,
                            @Value("${app.version}") String version) {
        this.animalService = animalService;
        this.title = title;
        this.serverPort = serverPort;
        this.version = version;
    }

    // 무작위 동물 페이지로 이동
    @GetMapping("/")
    public String randomAnimal() {
        Animal animal = animalService.findRandom();
        return "redirect:/animals/" + animal.getId();
    }

    // 동물 페이지 : 응답한 서버(호스트명 + 포트)를 함께 보여 줍니다
    @GetMapping("/animals/{id}")
    public String showAnimal(@PathVariable Long id, Model model) {
        model.addAttribute("title", title);
        model.addAttribute("animal", animalService.findById(id));
        model.addAttribute("hostname", hostname());
        model.addAttribute("port", serverPort);
        model.addAttribute("version", version);
        return "animal";
    }

    // 조회 수 +1 (상태를 바꾸는 요청이므로 POST 사용)
    @PostMapping("/animals/{id}/view")
    public String increaseView(@PathVariable Long id) {
        animalService.increaseView(id);
        return "redirect:/animals/" + id;
    }

    @GetMapping("/api/animals")
    @ResponseBody
    public List<Animal> listAnimals() {
        return animalService.findAll();
    }

    @GetMapping("/api/animals/{id}")
    @ResponseBody
    public Animal getAnimal(@PathVariable Long id) {
        return animalService.findById(id);
    }

    // 로컬에서는 PC 이름, 컨테이너에서는 컨테이너 ID가 나옵니다
    private String hostname() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return "unknown";
        }
    }
}
