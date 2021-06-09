package com.kavya.assignment;

import com.kavya.assignment.io.MalwareUrl;
import com.kavya.assignment.view.UrlState;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@RestController
public class AssignmentController {

    private final AtomicLong counter = new AtomicLong();

    /**
     * Controller which handles the URL to verify whether to block or allow the content
     * @param hostnameWithPort
     * @param pathParam
     * @param allQueryParams
     * @return
     */
    @GetMapping("/urlinfo/1/{hostnameWithPort}/{pathParam}")
    public UrlState handleMalwareURL(
            @PathVariable("hostnameWithPort") String hostnameWithPort,
            @PathVariable(value = "pathParam", required = false) String pathParam,
            @RequestParam Map<String,String> allQueryParams) {

        String pathWithQueryParam = pathParam;
        //combining path and query parameters
        if (allQueryParams != null && allQueryParams.size() > 0) {
            List<NameValuePair> nameValuePairList = allQueryParams
                    .keySet().stream().map( key -> new BasicNameValuePair(key, allQueryParams.get(key)))
                    .collect(Collectors.toList());
            final String queryString = URLEncodedUtils.format(nameValuePairList, Consts.UTF_8.toString());
            pathWithQueryParam += "?" + queryString;
        }

        //This is used to display teh content as BLCOKED if it matches the key value
        if (MalwareUrl.getBlockedUrls().containsKey(hostnameWithPort)) {
            List<String> pathList = MalwareUrl.getBlockedUrls().get(hostnameWithPort);
            if (pathList.contains(pathWithQueryParam)) {
                return new UrlState(counter.incrementAndGet(), "BLOCKED");
            }
        }
        //This is used to display teh content as ALLOWED if it doesn't matches the key value
        return new UrlState(counter.incrementAndGet(), "ALLOWED");
    }
}
