
package com.carwel.webmagic.config.filter;


import com.carwel.webmagic.config.resolver.JsonParamsHolder;


import javax.servlet.*;
import java.io.IOException;


public class JsonParamsHolderFilter implements Filter {

    /** 
     * @see Filter#init(FilterConfig)
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /** 
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        }finally {
            JsonParamsHolder.clear();
        }
    }

    /** 
     * @see Filter#destroy()
     */
    @Override
    public void destroy() {
    }

}
