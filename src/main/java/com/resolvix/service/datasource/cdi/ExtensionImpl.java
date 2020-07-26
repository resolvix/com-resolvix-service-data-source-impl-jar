package com.resolvix.service.datasource.cdi;

import com.resolvix.lib.javaee.BeanUtils;
import com.resolvix.service.management.api.event.Shutdown;
import com.resolvix.service.management.api.event.Startup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.*;

public class ExtensionImpl
    implements Extension
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ExtensionImpl.class);

    public ExtensionImpl() {
        //
    }

    private void postConstruct() {
        LOGGER.info("Extension::postConstruct invoked.");
    }

    private void initialiseCDI(
        BeanManager beanManager, @Observes BeforeBeanDiscovery beforeBeanDiscovery) {
        LOGGER.info("ExtensionImpl::initialiseCDI invoked.");
    }

    private void startCDI(
        BeanManager beanManager, @Observes AfterDeploymentValidation afterDeploymentValidation) {
        LOGGER.info("ExtensionImpl::startCDI invoked.");
    }

    private void initialisedApplicationScopedContext(
        BeanManager beanManager, @Observes @Initialized(ApplicationScoped.class) Object initialized) {
        LOGGER.info("ExtensionImpl::initialisedApplicationScopedContext invoked.");
    }

    private void destroyedApplicationScopedContext(
        BeanManager beanManager, @Observes @Destroyed(ApplicationScoped.class) Object destroyed) {
        LOGGER.info("ExtensionImpl::destroyedApplicationScopedContext invoked.");
    }

    private void stopCDI(
        BeanManager beanManager, @Observes BeforeShutdown beforeShutdown) {
        LOGGER.info("ExtensionImpl::stopCDI invoked.");
    }

    @PreDestroy
    private void preDestroy() {
        LOGGER.info("ExtensionImpl::preDestroy invoked.");
    }
}
