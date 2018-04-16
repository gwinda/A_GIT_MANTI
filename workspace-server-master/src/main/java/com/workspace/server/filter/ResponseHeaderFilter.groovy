package com.workspace.server.filter

import groovy.util.logging.Slf4j
import org.springframework.stereotype.Component
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
@WebFilter(filterName = "rspHeaderFilter", urlPatterns = "/*")
@Slf4j
class ResponseHeaderFilter implements Filter {

    @Override
    void init(FilterConfig filterConfig) throws ServletException {
        log.info('[workspace-server] Response Header Filter Initialize')
    }

    @Override
    void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse
        httpResponse.setHeader("Access-Control-Allow-Origin", httpServletRequest.getHeader("Origin"))
        httpResponse.setHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept")
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true")
        filterChain.doFilter(servletRequest, httpResponse)
    }

    @Override
    void destroy() {

    }

}
