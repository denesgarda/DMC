package com.denesgarda.DMC.properties;

import com.denesgarda.DMC.properties.lang.KeyValueMismatchException;
import com.denesgarda.DMC.properties.lang.OperationFailedException;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class PropertiesFile {
    private String path;

    public PropertiesFile(String path) {
        this.path = path;
    }

    public PropertiesFile setPath(String path) {
        this.path = path;
        return this;
    }

    public static PropertiesFile returnObject(String path) throws IOException {
        File file = new File(path);
        if (file.exists()) {
            return new PropertiesFile(path);
        } else {
            boolean successful = file.getParentFile().mkdirs() && file.createNewFile();
            if (successful) {
                return new PropertiesFile(path);
            } else {
                throw new OperationFailedException("Failed to create properties file.");
            }
        }
    }

    public String getPath() {
        return this.path;
    }

    public boolean exists() {
        File file = new File(this.path);
        return file.exists();
    }

    public String getProperty(String key) throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(this.path);
        properties.load(fileInputStream);
        String result = properties.getProperty(key);
        fileInputStream.close();
        return result;
    }

    public String getPropertyNotNull(String key, String defaultValue) throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(this.getPath());
        properties.load(fileInputStream);
        String result = properties.getProperty(key);
        fileInputStream.close();
        if (result == null) {
            this.setProperty(key, defaultValue);
            return defaultValue;
        }
        return result;
    }

    public PropertiesFile setProperty(String key, String value) throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(this.path);
        properties.load(fileInputStream);
        properties.setProperty(key, value);
        FileOutputStream fileOutputStream = new FileOutputStream(this.path);
        properties.store(fileOutputStream, "");
        fileInputStream.close();
        fileOutputStream.close();
        return this;
    }

    public PropertiesFile setProperty(String key, String value, String comment) throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(this.path);
        properties.load(fileInputStream);
        properties.setProperty(key, value);
        FileOutputStream fileOutputStream = new FileOutputStream(this.path);
        properties.store(fileOutputStream, comment);
        fileInputStream.close();
        fileOutputStream.close();
        return this;
    }

    public String[] getProperties(String[] keys) throws IOException {
        String[] result = new String[keys.length];
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(this.path);
        properties.load(fileInputStream);
        for (int i = 0; i < keys.length; i++) {
            result[i] = properties.getProperty(keys[i]);
        }
        fileInputStream.close();
        return result;
    }

    public PropertiesFile setProperties(@NotNull String[] keys, @NotNull String[] values) throws IOException {
        if (keys.length != values.length) {
            throw new KeyValueMismatchException("The number of keys does not match the number of values");
        }
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(this.path);
        properties.load(fileInputStream);
        for (int i = 0; i < keys.length; i++) {
            properties.put(keys[i], values[i]);
        }
        FileOutputStream fileOutputStream = new FileOutputStream(this.path);
        properties.store(fileOutputStream, "");
        fileInputStream.close();
        fileOutputStream.close();
        return this;
    }

    public PropertiesFile setProperties(@NotNull String[] keys, @NotNull String[] values, String comment) throws IOException {
        if (keys.length != values.length) {
            throw new KeyValueMismatchException("The number of keys does not match the number of values");
        }
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(this.path);
        properties.load(fileInputStream);
        for (int i = 0; i < keys.length; i++) {
            properties.put(keys[i], values[i]);
        }
        FileOutputStream fileOutputStream = new FileOutputStream(this.path);
        properties.store(fileOutputStream, comment);
        fileInputStream.close();
        fileOutputStream.close();
        return this;
    }

    public PropertiesFile removeProperty(String key) throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(this.path);
        properties.load(fileInputStream);
        properties.remove(key);
        FileOutputStream fileOutputStream = new FileOutputStream(this.path);
        properties.store(fileOutputStream, "");
        fileInputStream.close();
        fileOutputStream.close();
        return this;
    }

    public PropertiesFile removeProperty(String key, String comment) throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(this.path);
        properties.load(fileInputStream);
        properties.remove(key);
        FileOutputStream fileOutputStream = new FileOutputStream(this.path);
        properties.store(fileOutputStream, comment);
        fileInputStream.close();
        fileOutputStream.close();
        return this;
    }

    public PropertiesFile removeProperties(String[] keys) throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(this.path);
        properties.load(fileInputStream);
        for (String key : keys) {
            properties.remove(key);
        }
        FileOutputStream fileOutputStream = new FileOutputStream(this.path);
        properties.store(fileOutputStream, "");
        fileInputStream.close();
        fileOutputStream.close();
        return this;
    }

    public PropertiesFile removeProperties(String[] keys, String comment) throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(this.path);
        properties.load(fileInputStream);
        for (String key : keys) {
            properties.remove(key);
        }
        FileOutputStream fileOutputStream = new FileOutputStream(this.path);
        properties.store(fileOutputStream, comment);
        fileInputStream.close();
        fileOutputStream.close();
        return this;
    }

    public PropertiesFile clear() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(this.path);
        properties.load(fileInputStream);
        properties.clear();
        FileOutputStream fileOutputStream = new FileOutputStream(this.path);
        properties.store(fileOutputStream, "");
        fileInputStream.close();
        fileOutputStream.close();
        return this;
    }

    public boolean contains(String value) throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(this.path);
        properties.load(fileInputStream);
        boolean result = properties.contains(value);
        FileOutputStream fileOutputStream = new FileOutputStream(this.path);
        properties.store(fileOutputStream, "");
        fileInputStream.close();
        fileOutputStream.close();
        return result;
    }

    public boolean containsValue(String value) throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(this.path);
        properties.load(fileInputStream);
        boolean result = properties.containsValue(value);
        FileOutputStream fileOutputStream = new FileOutputStream(this.path);
        properties.store(fileOutputStream, "");
        fileInputStream.close();
        fileOutputStream.close();
        return result;
    }

    public boolean containsKey(String key) throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(this.path);
        properties.load(fileInputStream);
        boolean result = properties.containsKey(key);
        FileOutputStream fileOutputStream = new FileOutputStream(this.path);
        properties.store(fileOutputStream, "");
        fileInputStream.close();
        fileOutputStream.close();
        return result;
    }

    public File getAsFile() {
        return new File(this.path);
    }

    public boolean delete() {
        return new File(this.path).delete();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropertiesFile that = (PropertiesFile) o;
        return Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path);
    }
}
