/*
 * Copyright (c) 2017 Outsource Cafe, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.javagen.schema.model

class MModule extends MBase implements MSource
{
	Map<String,MModule> children = [:]
	Map<String,MField> fields = [:]
	List<MMethod> methods = []
	String partOf //Dart


	MModule() {
		name = ''
	}
	@Override
	String fullName() {
		parent ? parent.fullName()+'.'+name : name
	}
	boolean isRoot() {
		parent == null
	}
	def child(MModule m) {
		children[m.name] = m
		m.parent = this
	}
	String nestedAttr(String key)
	{
		attr[key] ?: parent?.nestedAttr(key)
	}

	def addField(MField f) {
		fields[f.name] = f
		f.parent = this
	}
	def addMethod(MMethod m) {
		methods << m
		m.parent = this
	}
	//make it play nice with some of the class methods
	boolean hasSuper() { false }
	boolean isInterface() { false }

}
