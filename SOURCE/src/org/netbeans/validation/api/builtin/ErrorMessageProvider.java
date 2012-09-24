/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.netbeans.validation.api.builtin;

/**
 *
 * @author Tim Boudreau
 */
public abstract class ErrorMessageProvider {
    public abstract String getMessage (Class<?> source, String messageId, String...args);
}
