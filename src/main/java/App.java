import config.Config;
import config.ConfigManager;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import utils.ObfuscationUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class App {
    static Logger logger = Logger.getLogger("main");
    private static Map<String, String> cache = new HashMap<>();
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        System.setProperty("file.encoding", "UTF-8");
        System.setProperty("http.agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
        logger.info("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        logger.info("         duckPageOptimizer 1.1v by Xmon         ");
        logger.info("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        new ConfigManager();
        Config c = ConfigManager.getConfig();
        final File f = new File(c.getInstance().getLOCATION());
        if (!f.exists()){
            System.exit(-1);
        }else{
            final Document doc = Jsoup.parse(f, "UTF-8");
            if (c.getInstance().isIMAGE()) {
                logger.info("Starting converting images to base64..");
                doc.select("img").forEach(x -> {
                    final File img = new File(x.attr("src"));
                    if (img.exists()) {
                        try {
                            String mimetype = Files.probeContentType(img.toPath());
                            if (mimetype != null && mimetype.split("/")[0].equals("image")) {
                                byte[] fileContent = FileUtils.readFileToByteArray(img);
                                String encodedString = Base64.getEncoder().encodeToString(fileContent);
                                doc.select(x.cssSelector()).attr("src", ("data:image/png;base64," + encodedString));
                                if (c.getInstance().isDEBUG()) System.out.println(img.getName() + " -> base64");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                logger.info("Done, all images converted!..");
            }
            if (c.getInstance().isCSSOBF()){
                StringBuilder sbb = new StringBuilder();
                logger.info("Starting search of classes..");
                doc.getElementsByAttribute("class").forEach(x ->{
                    x.classNames().forEach(y ->{
                        if (cache.get(y) == null){
                            final String s = ObfuscationUtils.obfuscation(y, c.getInstance().getOBFStrings());
                            cache.putIfAbsent(y, s);
                        }
                        sbb.append(y).append(" -> ").append(cache.get(y)).append("\n");
                        if (c.getInstance().isDEBUG()) System.out.println(y + " -> " + cache.get(y));
                    });
                });
                logger.info(String.format("Found %s css classes!", cache.size()));
                logger.info("Replacing classes html");
                Files.write(Paths.get("classy"),sbb.toString().getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
                doc.getElementsByAttribute("class").forEach(x ->{
                    x.attr("class", x.classNames().stream().map(cache::get).collect(Collectors.joining(" ")));
                });

                doc.head().select("link").forEach(x -> {
                    if (x.attr("rel").toLowerCase().equals("stylesheet")){
                        final File css = new File(x.attr("href"));
                        if (css.exists()){
                            try {
                                StringBuilder sb = new StringBuilder();
                                sb.append("/* Obfuscator by Xmon (https://github.com/Xmonpl) Please leave information about the author! */");
                                FileUtils.readLines(css, StandardCharsets.UTF_8).forEach(y ->{
                                    String test = y;
                                    final Matcher m = Pattern.compile("\\.[\\w\\-]+", Pattern.CASE_INSENSITIVE).matcher(test);
                                    while (m.find()){
                                        String match = m.group().replace(".", "");
                                        test = test.replaceAll("\\." + match.replaceAll("/[\\-\\[\\]\\/\\{\\}\\(\\)\\*\\+\\?\\.\\\\\\^\\$\\|]/g", "\\$&"), "." + replace(match));
                                    }
                                    final Matcher m2 =  Pattern.compile("/#[\\w\\-]+", Pattern.CASE_INSENSITIVE).matcher(test);
                                    while (m2.find()){
                                        String match = m2.group().replace("#", "");
                                        test = test.replaceAll("\\." + match.replaceAll("/[\\-\\[\\]\\/\\{\\}\\(\\)\\*\\+\\?\\.\\\\\\^\\$\\|]/g", "\\$&"), "#" + replace(match));
                                    }
                                    if (c.getInstance().isONELINE()) {
                                        sb.append(test);
                                    }else{
                                        sb.append(test).append("\n");
                                    }
                                });
                                sb.append("/* Obfuscator by Xmon (https://github.com/Xmonpl) Please leave information about the author! */");
                                Files.write(Paths.get(start + css.getName()),sb.toString().getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                doc.head().select("link").forEach(x ->{
                    if (x.attr("rel").toLowerCase().equals("stylesheet")){
                        final File css = new File(x.attr("href"));
                        x.attr("href",  + start + css.getName());
                    }
                });
            }
            if (c.getInstance().isCSSTRASHCODE()) doc.head().append(ObfuscationUtils.trashCodeCss());
            if (c.getInstance().isVIEWSOURCEBLOCKER()) doc.head().append("<script>/* PageEncryptor by Xmon - https://Xmon.cf *//* PageEncryptor by Xmon - https://Xmon.cf *//* PageEncryptor by Xmon - https://Xmon.cf */var _0x471c=['B\\x20O=[\\x271Y\\x27,\\x271r\\x27,\\x27b\\x5c1q=[\\x5c1j\\x5c1,\\x5c1l\\x5c1,\\x5c1k\\x5c1,\\x5c1m\\x5c1,\\x5c1n\\x5c1,\\x5c1o\\x5c1,\\x5c1p\\x5c1,\\x5c1s\\x5c1,\\x5c1t\\x5c1];(5(4,e){b\\x5cN=5(k){C(--k){4[\\x5c1u\\x5c1](4[\\x5c1v\\x5c1]())}};7(++e)}(c,D));b\\x5c1h=5(4,e){4=4-s;b\\x5cN=c[4];a\\x5cN};i[0(\\x5c1w\\x5c1)]=5(3){8(3[0(\\x5cq\\x5c1)]==j||3[0(\\x5cq\\x5c1)]==m||3[0(\\x5cq\\x5c1)]==l||3[0(\\x5cq\\x5c1)]==h||3[0(\\x5cq\\x5c1)]==n||3[0(\\x5cq\\x5c1)]==f||3[0(\\x5cq\\x5c1)]==t)a![]};i[0(\\x5c1x\\x5c1)]=5(2){8(2[0(\\x5cq\\x5c1)]==j||2[0(\\x5cq\\x5c1)]==m||2[0(\\x5cq\\x5c1)]==l||2[0(\\x5cq\\x5c1)]==h||2[0(\\x5cq\\x5c1)]==n||2[0(\\x5cq\\x5c1)]==f||2[0(\\x5cq\\x5c1)]==t)a![]};5\\x5c1y(6){8((6[0(\\x5cT\\x5c1)]||6[0(\\x5cq\\x5c1)])==f)6[0(\\x5cM\\x5c1)]()};$(d)[\\x5c1i\\x5c1](0(\\x5c11\\x5c1),p);$(d)[\\x5c15\\x5c1](0(\\x5c11\\x5c1),5(9){8(9[0(\\x5c1f\\x5c1)]&&9[0(\\x5cT\\x5c1)]==H){9[0(\\x5cM\\x5c1)]();a![]}});d[0(\\x5c1c\\x5c1)](0(\\x5c1b\\x5c1),o=>o[0(\\x5cM\\x5c1)]());\\x27,\\x2716\\x27,\\x2713\\x27,\\x271g|17|18|19|1a|u|1d|1e|Q|1z|z|B|1B|1A|1G|2b|2a|28|27|20|25|26|24|23|22|1Z|14|21|I|29|2c|1N|1X|1D|1E|1F|1H|1I|F|1J|Y|Z|10|1K|S|P|1L|12|1C|1M\\x27];(u(v,K){B\\x20G=u(X){F(--X){v[\\x27Y\\x27](v[\\x27Z\\x27]())}};G(++K)}(O,1O));B\\x20w=u(v,K){v=v-I;B\\x20G=O[v];z\\x20G};1P(u(J,E,r,A,y,L){y=u(x){z(x<E?\\x27\\x27:y(1Q(x/E)))+((x=x%E)>1R?V[w(\\x2712\\x27)](x+1S):x[w(\\x27P\\x27)](1T))};Q(!\\x27\\x27[w(\\x2714\\x27)](/^/,V)){F(r--){L[y(r)]=A[r]||y(r)}A=[u(R){z\\x20L[R]}];y=u(){z\\x27\\x5c1U+\\x27};r=P};F(r--){Q(A[r]){J=J[\\x2713\\x27](1V\\x201W(\\x27\\x5cU\\x27+y(r)+\\x27\\x5cU\\x27,\\x27g\\x27),A[r])}}z\\x20J}(w(\\x27I\\x27),W,W,w(\\x2710\\x27)[w(\\x27S\\x27)](\\x27|\\x27),I,{}));','toString','replace','\\x5cw+','split'];(function(_0x1868f2,_0x471ccd){var _0x233439=function(_0x571162){while(--_0x571162){_0x1868f2['push'](_0x1868f2['shift']());}};_0x233439(++_0x471ccd);}(_0x471c,0xca));var _0x2334=function(_0x1868f2,_0x471ccd){_0x1868f2=_0x1868f2-0x0;var _0x233439=_0x471c[_0x1868f2];return _0x233439;};eval(function(_0x383ddb,_0x165c68,_0x58996d,_0x440cfd,_0x2a7807,_0x212646){_0x2a7807=function(_0x451dd6){return(_0x451dd6<_0x165c68?'':_0x2a7807(parseInt(_0x451dd6/_0x165c68)))+((_0x451dd6=_0x451dd6%_0x165c68)>0x23?String['fromCharCode'](_0x451dd6+0x1d):_0x451dd6[_0x2334('0x4')](0x24));};if(!''[_0x2334('0x0')](/^/,String)){while(_0x58996d--){_0x212646[_0x2a7807(_0x58996d)]=_0x440cfd[_0x58996d]||_0x2a7807(_0x58996d);}_0x440cfd=[function(_0x3df1a3){return _0x212646[_0x3df1a3];}];_0x2a7807=function(){return _0x2334('0x1');};_0x58996d=0x1;};while(_0x58996d--){if(_0x440cfd[_0x58996d]){_0x383ddb=_0x383ddb[_0x2334('0x0')](new RegExp('\\x5cb'+_0x2a7807(_0x58996d)+'\\x5cb','g'),_0x440cfd[_0x58996d]);}}return _0x383ddb;}(_0x2334('0x3'),0x3e,0x89,'|x27|||||||||||||||||||||||||x271|_0x13ab72|||function|_0x5adcf7|_0x10a5|_0x468188|_0x5738bf|return|_0x5b2c2a|var|||_0x28b84b|while|_0x10a5f5||0x0|_0x2eb165|_0x4dad1c|_0x475653|x27g|x207|_0x4dad|0x1|if|_0x1d0f3d|0x4|x27r|x5cb|String|0x32|_0x35c4ac|push|shift|0x3|x27q|0x5|replace|0x2|x27K|toString|0x7|_0xfbd6ea|_0x5dfe97|_0x20ccf8|x27s|x27G|_0x3c29ec|_0x3c8704|x27I|_0x3c87|x200|x27M|x27N|x27v|x27u|x27w|x27x|x27y|x27z|x20c|fromCharCode|x27A|x27B|x27E|x27F|x27J|x27L|x20p|_0x6501e6|document|_0x24d3|on|onkeydown|keydown|addEventListener|_0x24d39e|ctrlKey|onkeypress|0xed|0x53|bind|which|preventDefault|0x1e2|eval|parseInt|0x23|0x1d|0x24|x5cw|new|RegExp|contextmenu|split|disableF5|0x7b|0x6|_0x374f33|0x4a|0x55|_0x19e03f|0x11|window|0x10|0x49|0x8|0x74|keyCode'[_0x2334('0x2')]('|'),0x0,{}));/* PageEncryptor by Xmon - https://Xmon.cf *//* PageEncryptor by Xmon - https://Xmon.cf *//* PageEncryptor by Xmon - https://Xmon.cf *//* PageEncryptor by Xmon - https://Xmon.cf */" + "</script>");
            if (c.getInstance().isONELINE()){
                Files.write(Paths.get(start + "-index.obf.html"), ("<!-- Obfuscator by Xmon (https://github.com/Xmonpl) Please leave information about the author! -->" + doc.html().replace("\n", "") + "<!-- Obfuscator by Xmon (https://github.com/Xmonpl) Please leave information about the author! -->").getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
            }else{
                Files.write(Paths.get(start + "-index.obf.html"), ("<!-- Obfuscator by Xmon (https://github.com/Xmonpl) Please leave information about the author! -->\n" + doc.html() + "\n<!-- Obfuscator by Xmon (https://github.com/Xmonpl) Please leave information about the author! -->").getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
            }
            logger.info(String.format("All operations took %s ms.", (System.currentTimeMillis() - start)));
        }
    }


    public static String replace(String s){
        if (cache.get(s) == null){
            return s;
        }
        return cache.get(s);
    }
}
