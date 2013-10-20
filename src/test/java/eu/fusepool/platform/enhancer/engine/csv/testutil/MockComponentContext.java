/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.fusepool.platform.enhancer.engine.csv.testutil;

import java.io.File;
import java.io.InputStream;
import java.util.Dictionary;
import java.util.Hashtable;

import org.apache.clerezza.rdf.core.serializedform.Parser;
import org.apache.log4j.Logger;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.BundleListener;
import org.osgi.framework.Filter;
import org.osgi.framework.FrameworkListener;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.ComponentInstance;

public class MockComponentContext implements ComponentContext {

    protected final Dictionary<String, Object> properties;

    public static String BUNDLE_TEST_DATA_FOLDER = "bundle-testdata"+File.separator ;
    
    
    
    public MockComponentContext() {
        properties = new Hashtable<String, Object>();
    }

    public MockComponentContext(Dictionary<String, Object> properties) {
        this.properties = properties;
    }

    public void disableComponent(String name) {
    }

    public void enableComponent(String name) {
    }

    public BundleContext getBundleContext() {
        return new BundleContext() {
        	
        	File bundleDataFolder ;
        	
            @Override
            public boolean ungetService(ServiceReference reference) {
                return false;
            }

            @Override
            public void removeServiceListener(ServiceListener listener) {
            }

            @Override
            public void removeFrameworkListener(FrameworkListener listener) {
            }

            @Override
            public void removeBundleListener(BundleListener listener) {
            }

            @SuppressWarnings("rawtypes")
			@Override
            public ServiceRegistration registerService(String clazz,
                    Object service, Dictionary properties) {
                return null;
            }

            @SuppressWarnings("rawtypes")
			@Override
            public ServiceRegistration registerService(String[] clazzes,
                    Object service, Dictionary properties) {
                return null;
            }

            @Override
            public Bundle installBundle(String location, InputStream input)
                    throws BundleException {
                return null;
            }

            @Override
            public Bundle installBundle(String location) throws BundleException {
                return null;
            }

            @Override
            public ServiceReference[] getServiceReferences(String clazz,
                    String filter) throws InvalidSyntaxException {
                return null;
            }

            @Override
            public ServiceReference getServiceReference(String clazz) {
            	if("org.osgi.service.log.LogService".equals(clazz)) {
            		return new MockLogServiceReference();
            	}
            	
               	if("org.apache.clerezza.rdf.core.serializedform.Parser".equals(clazz)) {
            		return new MockParserServiceReference() ;
            	}
            	
                return null;
            }

            @Override
            public Object getService(ServiceReference reference) {
            	if(reference instanceof MockLogServiceReference) {
            		return new MockLogService() ;
            	}
            	
            	if(reference instanceof MockParserServiceReference) {
            		return Parser.getInstance();
            	}

                return null;
            }

            @Override
            public String getProperty(String key) {
                return null;
            }

            @Override
            public File getDataFile(String filename) {
            	final String bundleDataFolderName ="."+File.separator+BUNDLE_TEST_DATA_FOLDER ;
            	
            	if(bundleDataFolder==null) {
            		bundleDataFolder = new File(bundleDataFolderName) ;
            		if(!bundleDataFolder.exists()) {
            			bundleDataFolder.mkdir() ;
            			Logger.getLogger(this.getClass()).info("Creating tmp dat folder: "+bundleDataFolder.getPath()) ;
            		}
            	}
            	
            	return new File(bundleDataFolderName+filename);
            }

            @Override
            public Bundle[] getBundles() {
                return null;
            }

            @Override
            public Bundle getBundle(long id) {
                return null;
            }

            @Override
            public Bundle getBundle() {
                return null;
            }

            @Override
            public ServiceReference[] getAllServiceReferences(String clazz,
                    String filter) throws InvalidSyntaxException {
                return null;
            }

            @Override
            public Filter createFilter(String filter)
                    throws InvalidSyntaxException {
                return null;
            }

            @Override
            public void addServiceListener(ServiceListener listener,
                    String filter) throws InvalidSyntaxException {

            }

            @Override
            public void addServiceListener(ServiceListener listener) {
            }

            @Override
            public void addFrameworkListener(FrameworkListener listener) {
            }

            @Override
            public void addBundleListener(BundleListener listener) {
            }
        };
    }

    public ComponentInstance getComponentInstance() {
        return null;
    }

    public Dictionary<String, Object> getProperties() {
        return properties;
    }

    public ServiceReference getServiceReference() {
        return null;
    }

    public Bundle getUsingBundle() {
        return null;
    }

    public Object locateService(String name) {
        return null;
    }

    public Object locateService(String name, ServiceReference reference) {
        return null;
    }

    public Object[] locateServices(String name) {
        return null;
    }

    
    class MockParserServiceReference implements ServiceReference {

		@Override
		public Object getProperty(String key) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String[] getPropertyKeys() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Bundle getBundle() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Bundle[] getUsingBundles() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isAssignableTo(Bundle bundle, String className) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public int compareTo(Object reference) {
			// TODO Auto-generated method stub
			return 0;
		}
    	
    }
    
    class MockLogServiceReference implements ServiceReference {

		@Override
		public Object getProperty(String key) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String[] getPropertyKeys() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Bundle getBundle() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Bundle[] getUsingBundles() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isAssignableTo(Bundle bundle, String className) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public int compareTo(Object reference) {
			// TODO Auto-generated method stub
			return 0;
		}
    	
    }
    
}
