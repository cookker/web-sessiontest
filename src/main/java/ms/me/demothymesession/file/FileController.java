package ms.me.demothymesession.file;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/files")
public class FileController {

    @GetMapping("")
    public String fileHome(Model model){
        return "/files/file";
    }

    @PostMapping("")
    public String uploadFile(@RequestParam MultipartFile file,
                             RedirectAttributes attributes){
        final String filename = file.getOriginalFilename();
        attributes.addFlashAttribute("fileName", filename);

        return "redirect:/files";
    }
}
