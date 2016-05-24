package com.firstutility.test.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class RequestResponseLoggingFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(RequestResponseLoggingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("doFilter()");

        // get request
        LoggingRequestWrapper loggingRequestWrapper = new LoggingRequestWrapper(
                (HttpServletRequest) servletRequest);
        servletRequest = loggingRequestWrapper;
        String requestContent = servletRequest.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        log.info("request JSON content : " + requestContent);

        LoggingResponseWrapper loggingResponseWrapper = new LoggingResponseWrapper(
                (HttpServletResponse) servletResponse);

        filterChain.doFilter(loggingRequestWrapper, loggingResponseWrapper);

        // get Json response
        if (servletResponse.getContentType() != null
                && servletResponse.getContentType().contains("application/json")) {
            String responseContent = loggingResponseWrapper.getCaptureAsString();
            log.info("response JSON content : " + responseContent);
        }
    }

    @Override
    public void destroy() {
    }
}
