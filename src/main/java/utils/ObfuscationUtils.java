package utils;

import config.Config;
import config.ConfigManager;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class ObfuscationUtils {
    private final static List<String> emoji = new ArrayList<>(Arrays.asList("😄", "😃", "😀", "😊", "☺", "😉", "😍", "😘", "😚", "😗", "😙", "😜", "😝", "😛", "😳", "😁", "😔", "😌", "😒", "😞", "😣", "😢", "😂", "😭", "😪", "😥", "😰", "😅", "😓", "😩", "😫", "😨", "😱", "😠", "😡", "😤", "😖", "😆", "😋", "😷", "😎", "😴", "😵", "😲", "😟", "😦", "😧", "😈", "👿", "😮", "😬", "😐", "😕", "😯", "😶", "😇", "😏", "😑", "👲", "👳", "👮", "👷", "💂", "👶", "👦", "👧", "👨", "👩", "👴", "👵", "👱", "👼", "👸", "😺", "😸", "😻", "😽", "😼", "🙀", "😿", "😹", "😾", "👹", "👺", "🙈", "🙉", "🙊", "💀", "👽", "💩", "🔥", "✨", "🌟", "💫", "💥", "💢", "💦", "💧", "💤", "💨", "👂", "👀", "👃", "👅", "👄", "👍", "👎", "👌", "👊", "✊", "✌", "👋", "✋", "👐", "👆", "👇", "👉", "👈", "🙌", "🙏", "☝", "👏", "💪", "🚶", "🏃", "💃", "👫", "👪", "👬", "👭", "💏", "💑", "👯", "🙆", "🙅", "💁", "🙋", "💆", "💇", "💅", "👰", "🙎", "🙍", "🙇", "🎩", "👑", "👒", "👟", "👞", "👡", "👠", "👢", "👕", "👔", "👚", "👗", "🎽", "👖", "👘", "👙", "💼", "👜", "👝", "👛", "👓", "🎀", "🌂", "💄", "💛", "💙", "💜", "💚", "❤", "💔", "💗", "💓", "💕", "💖", "💞", "💘", "💌", "💋", "💍", "💎", "👤", "👥", "💬", "👣", "💭", "🐶", "🐺", "🐱", "🐭", "🐹", "🐰", "🐸", "🐯", "🐨", "🐻", "🐷", "🐽", "🐮", "🐗", "🐵", "🐒", "🐴", "🐑", "🐘", "🐼", "🐧", "🐦", "🐤", "🐥", "🐣", "🐔", "🐍", "🐢", "🐛", "🐝", "🐜", "🐞", "🐌", "🐙", "🐚", "🐠", "🐟", "🐬", "🐳", "🐋", "🐄", "🐏", "🐀", "🐃", "🐅", "🐇", "🐉", "🐎", "🐐", "🐓", "🐕", "🐖", "🐁", "🐂", "🐲", "🐡", "🐊", "🐫", "🐪", "🐆", "🐈", "🐩", "🐾", "💐", "🌸", "🌷", "🍀", "🌹", "🌻", "🌺", "🍁", "🍃", "🍂", "🌿", "🌾", "🍄", "🌵", "🌴", "🌲", "🌳", "🌰", "🌱", "🌼", "🌐", "🌞", "🌝", "🌚", "🌑", "🌒", "🌓", "🌔", "🌕", "🌖", "🌗", "🌘", "🌜", "🌛", "🌙", "🌍", "🌎", "🌏", "🌋", "🌌", "🌠", "⭐", "☀", "⛅", "☁", "⚡", "☔", "❄", "⛄", "🌀", "🌁", "🌈", "🌊", "🎍", "💝", "🎎", "🎒", "🎓", "🎏", "🎆", "🎇", "🎐", "🎑", "🎃", "👻", "🎅", "🎄", "🎁", "🎋", "🎉", "🎊", "🎈", "🎌", "🔮", "🎥", "📷", "📹", "📼", "💿", "📀", "💽", "💾", "💻", "📱", "☎", "📞", "📟", "📠", "📡", "📺", "📻", "🔊", "🔉", "🔈", "🔇", "🔔", "🔕", "📢", "📣", "⏳", "⌛", "⏰", "⌚", "🔓", "🔒", "🔏", "🔐", "🔑", "🔎", "💡", "🔦", "🔆", "🔅", "🔌", "🔋", "🔍", "🛁", "🛀", "🚿", "🚽", "🔧", "🔩", "🔨", "🚪", "🚬", "💣", "🔫", "🔪", "💊", "💉", "💰", "💴", "💵", "💷", "💶", "💳", "💸", "📲", "📧", "📥", "📤", "✉", "📩", "📨", "📯", "📫", "📪", "📬", "📭", "📮", "📦", "📝", "📄", "📃", "📑", "📊", "📈", "📉", "📜", "📋", "📅", "📆", "📇", "📁", "📂", "✂", "📌", "📎", "✒", "✏", "📏", "📐", "📕", "📗", "📘", "📙", "📓", "📔", "📒", "📚", "📖", "🔖", "📛", "🔬", "🔭", "📰", "🎨", "🎬", "🎤", "🎧", "🎼", "🎵", "🎶", "🎹", "🎻", "🎺", "🎷", "🎸", "👾", "🎮", "🃏", "🎴", "🀄", "🎲", "🎯", "🏈", "🏀", "⚽", "⚾", "🎾", "🎱", "🏉", "🎳", "⛳", "🚵", "🚴", "🏁", "🏇", "🏆", "🎿", "🏂", "🏊", "🏄", "🎣", "☕", "🍵", "🍶", "🍼", "🍺", "🍻", "🍸", "🍹", "🍷", "🍴", "🍕", "🍔", "🍟", "🍗", "🍖", "🍝", "🍛", "🍤", "🍱", "🍣", "🍥", "🍙", "🍘", "🍚", "🍜", "🍲", "🍢", "🍡", "🍳", "🍞", "🍩", "🍮", "🍦", "🍨", "🍧", "🎂", "🍰", "🍪", "🍫", "🍬", "🍭", "🍯", "🍎", "🍏", "🍊", "🍋", "🍒", "🍇", "🍉", "🍓", "🍑", "🍈", "🍌", "🍐", "🍍", "🍠", "🍆", "🍅", "🌽", "🏠", "🏡", "🏫", "🏢", "🏣", "🏥", "🏦", "🏪", "🏩", "🏨", "💒", "⛪", "🏬", "🏤", "🌇", "🌆", "🏯", "🏰", "⛺", "🏭", "🗼", "🗾", "🗻", "🌄", "🌅", "🌃", "🗽", "🌉", "🎠", "🎡", "⛲", "🎢", "🚢", "⛵", "🚤", "🚣", "⚓", "🚀", "✈", "💺", "🚁", "🚂", "🚊", "🚉", "🚞", "🚆", "🚄", "🚅", "🚈", "🚇", "🚝", "🚋", "🚃", "🚎", "🚌", "🚍", "🚙", "🚘", "🚗", "🚕", "🚖", "🚛", "🚚", "🚨", "🚓", "🚔", "🚒", "🚑", "🚐", "🚲", "🚡", "🚟", "🚠", "🚜", "💈", "🚏", "🎫", "🚦", "🚥", "⚠", "🚧", "🔰", "⛽", "🏮", "🎰", "♨", "🗿", "🎪", "🎭", "📍", "🚩", "⬆", "⬇", "⬅", "➡", "🔠", "🔡", "🔤", "↗", "↖", "↘", "↙", "↔", "↕", "🔄", "◀", "▶", "🔼", "🔽", "↩", "↪", "ℹ", "⏪", "⏩", "⏫", "⏬", "⤵", "⤴", "🆗", "🔀", "🔁", "🔂", "🆕", "🆙", "🆒", "🆓", "🆖", "📶", "🎦", "🈁", "🈯", "🈳", "🈵", "🈴", "🈲", "🉐", "🈹", "🈺", "🈶", "🈚", "🚻", "🚹", "🚺", "🚼", "🚾", "🚰", "🚮", "🅿", "♿", "🚭", "🈷", "🈸", "🈂", "Ⓜ", "🛂", "🛄", "🛅", "🛃", "🉑", "㊙", "㊗", "🆑", "🆘", "🆔", "🚫", "🔞", "📵", "🚯", "🚱", "🚳", "🚷", "🚸", "⛔", "✳", "❇", "❎", "✅", "✴", "💟", "🆚", "📳", "📴", "🅰", "🅱", "🆎", "🅾", "💠", "➿", "♻", "♈", "♉", "♊", "♋", "♌", "♍", "♎", "♏", "♐", "♑", "♒", "♓", "⛎", "🔯", "🏧", "💹", "💲", "💱", "©", "®", "™", "〽", "〰", "🔝", "🔚", "🔙", "🔛", "🔜", "❌", "⭕", "❗", "❓", "❕", "❔", "🔃", "🕛", "🕧", "🕐", "🕜", "🕑", "🕝", "🕒", "🕞", "🕓", "🕟", "🕔", "🕠", "🕕", "🕖", "🕗", "🕘", "🕙", "🕚", "🕡", "🕢", "🕣", "🕤", "🕥", "🕦", "✖", "➕", "➖", "➗", "♠", "♥", "♣", "♦", "💮", "💯", "✔", "☑", "🔘", "🔗", "➰", "🔱", "🔲", "🔳", "◼", "◻", "◾", "◽", "▪", "▫", "🔺", "⬜", "⬛", "⚫", "⚪", "🔴", "🔵", "🔻", "🔶", "🔷", "🔸", "🔹"));

    public static String obfuscation(final String oldString, String obf){
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            int ii = (int)(obf.length() * Math.random());
            sb.append(obf.charAt(ii));
        }
        return "xmon-" + sb.toString() + "-" + emoji.get(new Random().nextInt(emoji.size()));
    }
    public static String randomStringTrash(){
        String aa = "ﻓﺵﺵﺷﺷﺻﺿﺦ﷼﷽ﯸﯷﯲﷺﷻ﷼ﺄﱡﯯﯮﯫﯢﯟﻼﻢﺹשּׂﭚﭾﭴוּꭣꭄ";
        StringBuilder sb = new StringBuilder(32);
        for (int i = 0; i < 32; i++) {
            int ii = (int)(aa.length() * Math.random());
            sb.append(aa.charAt(ii));
        }
        return "xmon-" + sb.toString() + "-" + emoji.get(new Random().nextInt(emoji.size()));
    }

    public static String trashCodeCss(){
        List<String> content = Arrays.asList("-webkit-transform:translate3d(2000px,0,0);", "opacity:0;", "transform:translate3d(2000px,0,0);", "-webkit-transform:translate3d(100%,0,0) skewX(30deg);", "-webkit-transform: rotate(-360deg);", "-webkit-transform: rotate(0deg);", "transform: rotate(-360deg);", "background: #121212;", "z-index: 10;", "height: 100%;", "width: 51%;", "top: 0;", "position: fixed;", "position: absolute;", "transition: 0.3s;", "text-decoration: none;", "border-radius: 0 5px 5px 0;", "color: white;", "font-size: 20px;", "text-decoration: none;", "width: 100px;", "padding: 15px;", "left: -80px;", "background-color: #555;", "transition: all 0.7s 0.3s cubic-bezier(0.645, 0.045, 0.355, 1);", "transition: all 0.3s ease-out;", "opacity: 0;", "-webkit-transform: translateY(-100%);", "visibility: hidden;", "transition: all 0.3s 1s ease-out;", "overflow: hidden;", "position: relative;", "display: none;", "-webkit-transition: all 0.7s 0.3s cubic-bezier(0.645, 0.045, 0.355, 1);", "-webkit-animation-timing-function:cubic-bezier(.215,.61,.355,1);", "background-color: #4527A0;", "color: white;", "overflow-y: hidden;", "overflow-z: hidden;", "-webkit-background-size: cover;", "-moz-background-size: cover;", "-o-background-size: cover;", "background-size: cover;", "filter: blur(4px) brightness(0.5);", "position: absolute;", "top: 0; left: 0;", "height: 100%; width:100%;", " box-shadow: inset 0 0 100rem rgb(0, 0, 0);", "overflow: hidden;", "background-repeat: no-repeat; background-position: 50% 4%;", " background-color:hsla(0,28%,1%,0.3);", "margin-top: 20px;", "margin-top: 15px;", "font-family: 'Didact Gothic', regular;", "font-size: 1.5em;", "margin-left: 5%;", "font-size: 0.9em;", "margin-bottom: 1px;", "letter-spacing: 10.6667px;", "text-indent: 0px;");
        StringBuilder sb = new StringBuilder();
        Config c = ConfigManager.getConfig();
        sb.append("<style>");
        for (long i = 0; i < c.getInstance().getCSSTRASHCODESIZE(); i++) {
            Collections.shuffle(content);
            if (c.getInstance().isONELINE()){
                sb.append("/* PageEncryptor by Xmon - https://Xmon.cf */");
                sb.append(randomStringTrash()).append(" {");
                content.forEach(sb::append);
                sb.append("/* PageEncryptor by Xmon - https://Xmon.cf */");
                sb.append("}");
                sb.append("/* PageEncryptor by Xmon - https://Xmon.cf */");
            }else{
                sb.append("/* PageEncryptor by Xmon - https://Xmon.cf */");
                sb.append(randomStringTrash()).append(" {\n");
                content.forEach(x ->{
                    sb.append("\t").append(x).append("\n");
                });
                sb.append("/* PageEncryptor by Xmon - https://Xmon.cf */");
                sb.append("}\n");
            }
        }
        sb.append("</style>");
        return sb.toString();
    }

}
