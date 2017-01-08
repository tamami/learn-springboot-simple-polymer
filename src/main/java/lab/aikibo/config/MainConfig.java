package lab.aikibo.config;

import com.jaunt.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tamami on 04/01/17.
 */
@Controller
public class MainConfig {

    @RequestMapping("/")
    public String getRoot() {
        return "index";
    }

    @RequestMapping("/formnip")
    public Map<String, Object> formNip() {
        return null;
    }

    @RequestMapping("/getNipWeb")
    @ResponseBody
    public String getNipInfo(@RequestParam(value="nip") String nip, Model model) {
        UserAgent userAgent = new UserAgent();
        try {
            userAgent.visit("http://www.bkn.go.id/profil-pns");
        } catch(ResponseException e) { e.printStackTrace(); }

        try {
            userAgent.sendPOST("http://www.bkn.go.id/profil-pns", "nip=" + nip);
            Elements element = userAgent.doc.findEvery("<span>");
            int i=1;
            for(Element el : element) {
                if(el.getAt("class").equals("value")) {
                    model.addAttribute("data" + i, el.innerHTML().substring(2));
                    i++;
                }
            }
        } catch(NotFound e) {}
          catch(SearchException e) { e.printStackTrace(); }
          catch (ResponseException e) { e.printStackTrace(); }

        return "result";
    }

    @RequestMapping("/getNipRest")
    public Map<String, Object> getNipWeb(@RequestParam("nip") String nip, Model model) {
        UserAgent userAgent = new UserAgent();
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            userAgent.visit("http://www.bkn.go.id/profil-pns");
        } catch(ResponseException e) { e.printStackTrace(); }

        try {
            userAgent.sendPOST("http://www.bkn.go.id/profil-pns", "nip=" + nip);
            Elements element = userAgent.doc.findEvery("<span>");
            int i=1;
            for(Element el : element) {
                if(el.getAt("class").equals("value")) {
                    model.addAttribute("data" + i, el.innerHTML().substring(2));
                    i++;
                }
            }
        } catch(NotFound e) {}
        catch(SearchException e) { e.printStackTrace(); }
        catch (ResponseException e) { e.printStackTrace(); }

        return result;
    }


}
