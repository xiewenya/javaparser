/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser.
 * Copyright (C) 2011, 2013-2016 The JavaParser Team.
 *
 * This file is part of JavaParser.
 *
 * JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * b) the terms of the Apache License
 *
 * You should have received a copy of both licenses in LICENCE.LGPL and
 * LICENCE.APACHE. Please refer to those files for details.
 *
 * JavaParser is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 */

package com.github.javaparser.ast.imports;

import com.github.javaparser.Range;
import com.github.javaparser.ast.observer.ObservableProperty;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;

import static com.github.javaparser.utils.Utils.assertNotNull;

/**
 * Example: <code>import static com.github.javaparser.JavaParser.*;</code> <p><a href="https://docs.oracle.com/javase/specs/jls/se8/html/jls-7.html#jls-7.5.4">7.5.4.
 * Static-Import-on-Demand Declarations</a></p>
 */
public class StaticImportOnDemandDeclaration extends ImportDeclaration {
    private ClassOrInterfaceType type;

    public StaticImportOnDemandDeclaration() {
        this(null, new ClassOrInterfaceType());
    }

    public StaticImportOnDemandDeclaration(Range range, ClassOrInterfaceType type) {
        super(range);
        setType(type);
    }

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return v.visit(this, arg);
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {
        v.visit(this, arg);
    }

    public ClassOrInterfaceType getType() {
        return type;
    }

    public StaticImportOnDemandDeclaration setType(ClassOrInterfaceType type) {
        notifyPropertyChange(ObservableProperty.TYPE, this.type, type);
        this.type = assertNotNull(type);
        setAsParentNodeOf(type);
        return this;
    }

    @Override
    public boolean isAsterisk() {
        return true;
    }

    @Override
    public boolean isStatic() {
        return true;
    }
}
